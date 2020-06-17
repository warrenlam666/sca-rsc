package org.warren.sca.rsc.postmaninfo.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("postman")
@Data
public class PostmanPO implements Serializable {

    private int id;

    private String username;

    private String password;

    public PostmanPO() {
    }

    public PostmanPO(String username, String encryptionPassword) {
    }
}
