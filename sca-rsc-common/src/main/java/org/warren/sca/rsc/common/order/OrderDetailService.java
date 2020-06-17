package org.warren.sca.rsc.common.order;

import org.warren.sca.rsc.common.order.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {

    /*订单创建*/
    void updateToOC(long orderId);

    /*订单分配收件快递员*/
    void updateToDRP(long orderId, int postmanId);

    /*订单取消*/
    void updateToCancel(long orderId);

    /*订单快递已收件*/
    void updateToRP(long orderId, int postmanId);

    /*订单快递已发出*/
    void updateToSP(long orderId, int postmanId);

    /*订单快递分配派件快递员*/
    void updateToDDP(long orderId, int postmanId);

    /*订单已完成*/
    void updateToFN(long orderId, int postman);

    List<OrderDetailDTO> getOrderDetails(long orderMasterId);

    OrderDetailDTO getOrderDetail(int orderDetailId);

}
