package org.warren.sca.rsc.common.order;

import org.warren.sca.rsc.common.order.dto.CustomerOrderInfoDTO;
import org.warren.sca.rsc.common.order.dto.OrderDetailDTO;
import org.warren.sca.rsc.common.order.dto.OrderMasterDTO;

import java.util.List;

public interface OrderInfoService {

    CustomerOrderInfoDTO getOrderInfo(long orderId);


}
