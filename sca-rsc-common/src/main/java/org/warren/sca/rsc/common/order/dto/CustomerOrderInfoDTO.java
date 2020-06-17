package org.warren.sca.rsc.common.order.dto;

import java.io.Serializable;
import java.util.List;


public class CustomerOrderInfoDTO implements Serializable {

    private OrderMasterDTO masterDTO;

    private List<OrderDetailDTO> detailDTOS;

    public CustomerOrderInfoDTO() {
    }

    public CustomerOrderInfoDTO(OrderMasterDTO masterDTO, List<OrderDetailDTO> detailDTOS) {
        this.masterDTO = masterDTO;
        this.detailDTOS = detailDTOS;
    }

    public OrderMasterDTO getMasterDTO() {
        return masterDTO;
    }

    public void setMasterDTO(OrderMasterDTO masterDTO) {
        this.masterDTO = masterDTO;
    }

    public List<OrderDetailDTO> getDetailDTOS() {
        return detailDTOS;
    }

    public void setDetailDTOS(List<OrderDetailDTO> detailDTOS) {
        this.detailDTOS = detailDTOS;
    }
}
