package org.warren.sca.rsc.customerinfo.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("customer")
public class CustomerDO implements Serializable {

    private int id;

    private String username;

    private String password;

    public CustomerDO() {
    }

    public CustomerDO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
