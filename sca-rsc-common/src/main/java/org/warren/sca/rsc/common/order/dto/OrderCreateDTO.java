package org.warren.sca.rsc.common.order.dto;

import java.io.Serializable;

public class OrderCreateDTO implements Serializable {

    private int sendContactId;

    private int receiverContactId;

    public OrderCreateDTO() {
    }

    public int getSendContactId() {
        return sendContactId;
    }

    public void setSendContactId(int sendContactId) {
        this.sendContactId = sendContactId;
    }

    public int getReceiverContactId() {
        return receiverContactId;
    }

    public void setReceiverContactId(int receiverContactId) {
        this.receiverContactId = receiverContactId;
    }
}
