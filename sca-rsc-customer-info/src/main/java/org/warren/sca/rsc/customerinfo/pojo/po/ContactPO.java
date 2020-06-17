package org.warren.sca.rsc.customerinfo.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("contact")
@Data
public class ContactPO implements Serializable {

    @TableId
    private int id;

    private String name;

    private String phone;

    private int areaId;

    private String detail;

    private int customerId;

}
