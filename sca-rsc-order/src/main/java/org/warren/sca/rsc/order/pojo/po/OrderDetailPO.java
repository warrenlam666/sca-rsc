package org.warren.sca.rsc.order.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;

import java.io.Serializable;
import java.sql.Timestamp;

@TableName("order_detail")
@Builder
public class OrderDetailPO implements Serializable {

    private int id;

    private int postmanId;

    private int customerId;

    private long orderMasterId;

    private int type;

    private String message;

    private Timestamp time;

}
