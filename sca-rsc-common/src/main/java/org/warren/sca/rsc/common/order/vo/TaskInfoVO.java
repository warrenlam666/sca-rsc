package org.warren.sca.rsc.common.order.vo;

import org.warren.sca.rsc.common.order.dto.OrderDetailDTO;
import org.warren.sca.rsc.common.order.dto.OrderMasterDTO;

import java.sql.Timestamp;
import java.util.List;

public class TaskInfoVO {

    private OrderMasterDTO orderMasterDTO;

    private List<OrderDetailDTO> details;

    public TaskInfoVO() {
    }

    public TaskInfoVO(OrderMasterDTO orderMasterDTO, List<OrderDetailDTO> details) {
        this.orderMasterDTO = orderMasterDTO;
        this.details = details;
    }

    public OrderMasterDTO getOrderMasterDTO() {
        return orderMasterDTO;
    }

    public void setOrderMasterDTO(OrderMasterDTO orderMasterDTO) {
        this.orderMasterDTO = orderMasterDTO;
    }

    public List<OrderDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailDTO> details) {
        this.details = details;
    }
}
