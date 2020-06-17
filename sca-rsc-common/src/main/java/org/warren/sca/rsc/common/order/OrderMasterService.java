package org.warren.sca.rsc.common.order;

import org.warren.sca.rsc.common.order.dto.OrderCreateDTO;
import org.warren.sca.rsc.common.order.dto.OrderMasterDTO;

public interface OrderMasterService {

    void create(OrderCreateDTO orderCreateDTO, int customerId);

    void cancel(long orderMasterId);

    OrderMasterDTO getOrderMasterById(long id);

    long[] getIdsByCustomerId(int customerId);

}
