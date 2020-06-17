package org.warren.sca.rsc.customerinfo.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("area")
public class AreaPO implements Serializable {

    private int id;

    private String name;



}
