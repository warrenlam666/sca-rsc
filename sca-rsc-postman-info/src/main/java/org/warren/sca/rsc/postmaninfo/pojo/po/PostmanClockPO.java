package org.warren.sca.rsc.postmaninfo.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName("postman_clock")
public class PostmanClockPO implements Serializable {

    private int id;

    private int postmanId;

    private Timestamp time;

    private int type;

    public PostmanClockPO() {
    }

    public PostmanClockPO(int id, int postmanId, Timestamp time, int type) {
        this.id = id;
        this.postmanId = postmanId;
        this.time = time;
        this.type = type;
    }
}
