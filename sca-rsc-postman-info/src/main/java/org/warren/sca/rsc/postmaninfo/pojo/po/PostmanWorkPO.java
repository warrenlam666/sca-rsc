package org.warren.sca.rsc.postmaninfo.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName("postman_work")
public class PostmanWorkPO implements Serializable {

    private int id;

    private int postmanId;

    private long orderMasterId;

    private int type;

    private int status;

    private Timestamp time;

    public PostmanWorkPO(int id, int postmanId, long orderMasterId, int type, int status, Timestamp time) {
        this.id = id;
        this.postmanId = postmanId;
        this.orderMasterId = orderMasterId;
        this.type = type;
        this.status = status;
        this.time = time;
    }

    public PostmanWorkPO(int postmanId, long orderMasterId, int type, int status, Timestamp time) {
        this.postmanId = postmanId;
        this.orderMasterId = orderMasterId;
        this.type = type;
        this.status = status;
        this.time = time;
    }
}
