package org.warren.sca.rsc.order.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.warren.sca.rsc.common.order.OrderMasterService;
import org.warren.sca.rsc.common.order.OrderDetailService;
import org.warren.sca.rsc.common.order.OrderInfoService;
import org.warren.sca.rsc.common.order.dto.CustomerOrderInfoDTO;

@Service
@org.apache.dubbo.config.annotation.Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderMasterService orderBaseService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public CustomerOrderInfoDTO getOrderInfo(long orderId) {
        return new CustomerOrderInfoDTO(
                orderBaseService.getOrderMasterById(orderId),
                orderDetailService.getOrderDetails(orderId)
        );
    }

}
