package org.warren.sca.rsc.common.order.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderDetailDTO implements Serializable {

    private int id;

    private int postmanId;

    private int customerId;

    private int orderMasterId;

    private int type;

    private String message;

    private Timestamp time;

    public OrderDetailDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostmanId() {
        return postmanId;
    }

    public void setPostmanId(int postmanId) {
        this.postmanId = postmanId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOrderMasterId() {
        return orderMasterId;
    }

    public void setOrderMasterId(int orderMasterId) {
        this.orderMasterId = orderMasterId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
