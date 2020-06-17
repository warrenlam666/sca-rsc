package org.warren.sca.rsc.order.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.seata.core.context.RootContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.warren.sca.rsc.common.order.OrderDetailService;
import org.warren.sca.rsc.common.order.dto.OrderDetailDTO;
import org.warren.sca.rsc.common.order.constant.OrderDetailConstant;
import org.warren.sca.rsc.order.mapper.OrderDetailPOMapper;
import org.warren.sca.rsc.order.pojo.po.OrderDetailPO;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
@org.apache.dubbo.config.annotation.Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailPOMapper orderDetailPOMapper;

    private final static int SYSTEM_OPERATE = 0;

    @Override
    public void updateToOC(long orderId) {
        OrderDetailPO orderDetail = OrderDetailPO.builder()
                .time(new Timestamp(System.currentTimeMillis()))
                .orderMasterId(orderId)
                .type(OrderDetailConstant.ORDER_CREATE)
                .message("订单已经创建")
                .postmanId(SYSTEM_OPERATE)
                .build();
        orderDetailPOMapper.insert(orderDetail);
    }

    @Override
    public void updateToDRP(long orderId, int postmanId) {
        OrderDetailPO orderDetail = OrderDetailPO.builder()
                .time(new Timestamp(System.currentTimeMillis()))
                .orderMasterId(orderId)
                .type(OrderDetailConstant.ORDER_DISPATCHER_RECEIVER_POSTMAN)
                .message("订单已分配快递员")
                .postmanId(SYSTEM_OPERATE)
                .build();
        orderDetailPOMapper.insert(orderDetail);
    }

    @Override
    public void updateToCancel(long orderId) {
        OrderDetailPO orderDetail = OrderDetailPO.builder()
                .time(new Timestamp(System.currentTimeMillis()))
                .orderMasterId(orderId)
                .type(OrderDetailConstant.ORDER_CANCEL)
                .message("订单已经取消")
                .postmanId(SYSTEM_OPERATE)
                .build();
        orderDetailPOMapper.insert(orderDetail);
    }

    @Override
    public void updateToRP(long orderId, int postmanId) {
        OrderDetailPO orderDetail = OrderDetailPO.builder()
                .time(new Timestamp(System.currentTimeMillis()))
                .orderMasterId(orderId)
                .type(OrderDetailConstant.ORDER_RECEIVER_PACKAGE)
                .message("快递员已收件")
                .postmanId(postmanId)
                .build();
        orderDetailPOMapper.insert(orderDetail);
    }

    @Override
    public void updateToSP(long orderId, int postmanId) {
        OrderDetailPO orderDetail = OrderDetailPO.builder()
                .time(new Timestamp(System.currentTimeMillis()))
                .orderMasterId(orderId)
                .type(OrderDetailConstant.ORDER_SEND_PACKAGE)
                .message("快递已发出")
                .postmanId(postmanId)
                .build();
        orderDetailPOMapper.insert(orderDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateToDDP(long orderId, int postmanId) {
        System.out.println("xid: "+ RootContext.getXID());
        OrderDetailPO orderDetail = OrderDetailPO.builder()
                .time(new Timestamp(System.currentTimeMillis()))
                .orderMasterId(orderId)
                .type(OrderDetailConstant.ORDER_DISPATCHER_DISPATCHER_POSTMAN)
                .message("快递员已分配派件快递员")
                .postmanId(postmanId)
                .build();
        orderDetailPOMapper.insert(orderDetail);
    }

    @Override
    public void updateToFN(long orderId, int postmanId) {
        OrderDetailPO orderDetail = OrderDetailPO.builder()
                .time(new Timestamp(System.currentTimeMillis()))
                .orderMasterId(orderId)
                .type(OrderDetailConstant.ORDER_RECEIVER_PACKAGE)
                .message("订单已派件完成")
                .postmanId(postmanId)
                .build();
        orderDetailPOMapper.insert(orderDetail);
    }

    @Override
    public List<OrderDetailDTO> getOrderDetails(long orderMasterId) {
        return orderDetailPOMapper.selectList(new QueryWrapper<OrderDetailPO>().eq("order_master_id", orderMasterId))
                .stream()
                .map(this::boxingPOToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public OrderDetailDTO getOrderDetail(int orderDetailId) {
        return boxingPOToDTO(orderDetailPOMapper.selectById(orderDetailId));
    }

    public OrderDetailDTO boxingPOToDTO(OrderDetailPO po){
        if (po == null)
            return null;
        OrderDetailDTO detailDTO = new OrderDetailDTO();
        BeanUtils.copyProperties(po, detailDTO);
        return detailDTO;
    }
}
