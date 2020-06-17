package org.warren.sca.rsc.order.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.warren.sca.rsc.common.order.OrderMasterService;
import org.warren.sca.rsc.common.order.SnowFlakeService;
import org.warren.sca.rsc.common.order.dto.OrderCreateDTO;
import org.warren.sca.rsc.common.order.dto.OrderMasterDTO;
import org.warren.sca.rsc.order.mapper.OrderDetailPOMapper;
import org.warren.sca.rsc.order.mapper.OrderMasterDOMapper;
import org.warren.sca.rsc.order.pojo.po.OrderMasterPO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@org.apache.dubbo.config.annotation.Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterDOMapper orderMasterDOMapper;

    @Autowired
    private OrderDetailPOMapper orderDetailPOMapper;

    @Autowired
    private SnowFlakeService snowFlakeService;

    @Autowired
    private MySource mySource;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void create(OrderCreateDTO orderCreateDTO, int customerId) {
        OrderMasterPO orderMasterPO = new OrderMasterPO();
        BeanUtils.copyProperties(orderCreateDTO, orderMasterPO);
        orderMasterPO.setCustomerId(customerId);
        orderMasterPO.setCreateTime(new Timestamp(new Date().getTime()));
        orderMasterPO.setLastDetailId(1);
        orderMasterPO.setId(snowFlakeService.generate());
        orderMasterDOMapper.insert(orderMasterPO);
        mySource.customerNewOrderTopic().send(MessageBuilder.withPayload(orderMasterPO.getId()).build());
        logger.debug("向rocketMQ发送消息:{}", orderMasterPO.getId());
    }

    @Override
    public void cancel(long orderMasterId) {
        //还在努力
    }

    @Override
    public OrderMasterDTO getOrderMasterById(long id) {
        OrderMasterPO orderMasterPO = orderMasterDOMapper.selectById(id);
        if (orderMasterPO == null)
            return null;
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties(orderMasterPO, orderMasterDTO);
        return orderMasterDTO;
    }

    @Override
    public long[] getIdsByCustomerId(int customerId) {
        List<OrderMasterPO> pos = orderMasterDOMapper.selectList(new QueryWrapper<OrderMasterPO>().select("id").eq("customer_id", customerId));
        return pos.stream().mapToLong(OrderMasterPO::getId).toArray();
    }
}
