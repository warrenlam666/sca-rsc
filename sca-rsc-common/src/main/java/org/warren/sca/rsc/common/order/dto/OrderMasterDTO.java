package org.warren.sca.rsc.common.order.dto;


import java.io.Serializable;
import java.sql.Timestamp;

public class OrderMasterDTO implements Serializable {

    private int id;

    private int customerId;

    private int sendContactId;

    private int receiverContactId;

    private int lastDetailId;

    private Timestamp createTime;

    public OrderMasterDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public int getLastDetailId() {
        return lastDetailId;
    }

    public void setLastDetailId(int lastDetailId) {
        this.lastDetailId = lastDetailId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
