package org.warren.sca.rsc.common.postmaninfo.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class PostmanWorkDTO implements Serializable {

    private int id;

    private int postmanId;

    private long orderMasterId;

    private int type;

    private int status;

    private Timestamp time;

    public PostmanWorkDTO() {
    }

    public PostmanWorkDTO(int postmanId, long orderMasterId, int type, Timestamp time) {
        this.postmanId = postmanId;
        this.orderMasterId = orderMasterId;
        this.type = type;
        this.time = time;
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

    public long getOrderMasterId() {
        return orderMasterId;
    }

    public void setOrderMasterId(long orderMasterId) {
        this.orderMasterId = orderMasterId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
