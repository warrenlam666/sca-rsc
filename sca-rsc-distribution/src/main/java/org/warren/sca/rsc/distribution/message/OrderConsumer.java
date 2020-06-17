package org.warren.sca.rsc.distribution.message;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.warren.sca.rsc.common.customerinfo.CustomerContactService;
import org.warren.sca.rsc.common.distribution.DistributionOrderPoolService;
import org.warren.sca.rsc.common.order.OrderMasterService;
import org.warren.sca.rsc.common.order.OrderDetailService;
import org.warren.sca.rsc.common.order.constant.OrderDetailConstant;
import org.warren.sca.rsc.common.order.dto.OrderDetailDTO;
import org.warren.sca.rsc.common.order.dto.OrderMasterDTO;
import org.warren.sca.rsc.common.postmaninfo.PostmanWorkService;

import java.util.*;

@Component
@Slf4j
public class OrderConsumer {

    @Reference
    private CustomerContactService customerContactService;

    @Reference
    private OrderMasterService orderBaseService;

    @Reference
    private OrderDetailService orderDetailService;

    @Reference
    private PostmanWorkService postmanWorkService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DefaultRedisScript<String> defaultRedisScript;

    @Autowired
    private DistributionOrderPoolService distributionOrderPoolService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @StreamListener(MessageSource.CUSTOMER_NEW_ORDER_INPUT)
    @GlobalTransactional
    public void customerNewOrderHandler(@Payload long orderId){
        OrderMasterDTO masterDTO = orderBaseService.getOrderMasterById(orderId);
        if (getOrderLatestStatus(masterDTO) != OrderDetailConstant.ORDER_CREATE){   //重复消费
            return;
        }

        logger.info("[配送中心消费消息]收件订单id:{}",orderId);
        int areaId = customerContactService.getContactByContactId(masterDTO.getSendContactId()).getAreaId();
        int postmanId = findPostman(areaId);
        if (postmanId == -1){
            //加入订单池
            distributionOrderPoolService.appendOrder(orderId);
            logger.info("[配送中心消费消息]订单号{}加入订单池", orderId);
            return;
        }


        logger.info("[配送中心消费消息]订单{}分配到快递员{}",orderId, postmanId);
        //全局分布式事务保证一致性和原子性
        orderDetailService.updateToDDP(orderId, postmanId);
        postmanWorkService.addPickUpTask(orderId, postmanId);
    }

    @StreamListener(MessageSource.CUSTOMER_OLD_ORDER_INPUT)
    @GlobalTransactional
    public void customerOldOrderHandler(@Payload long orderId){
        OrderMasterDTO masterDTO = orderBaseService.getOrderMasterById(orderId);
        if (getOrderLatestStatus(masterDTO) != OrderDetailConstant.ORDER_SEND_PACKAGE){ //消息重复消费
            return;
        }

        logger.info("[配送中心消费消息]派件订单id:{}",orderId);
        int areaId = customerContactService.getContactByContactId(masterDTO.getReceiverContactId()).getAreaId();
        int postmanId = findPostman(areaId);
        logger.info("[配送中心消费消息]派件订单号{}分配到快递员{}", orderId, postmanId);
        if (postmanId == -1){
            return;
        }

        orderDetailService.updateToDDP(orderId, postmanId);
        postmanWorkService.addDistributionTask(orderId, postmanId);

    }

    public int findPostman(int areaId){
        //分配快递员对redis操作有两个步骤：选定score最小的快递员，并调整score。为保证这两步操作的原子性故需用到lua命令
        return Integer.parseInt(
                Objects.requireNonNull(
                        redisTemplate.execute(defaultRedisScript, Collections.singletonList("POSTMAN_OF_AREA_" + areaId)
                        )
                )
        );
    }

    public int getOrderLatestStatus(OrderMasterDTO masterDTO){
        return Optional.ofNullable(masterDTO)
                .map(OrderMasterDTO::getLastDetailId)
                .map(detailId->orderDetailService.getOrderDetail(detailId))
                .map(OrderDetailDTO::getType)
                .orElse(OrderDetailConstant.ORDER_INVALID);
    }

}
