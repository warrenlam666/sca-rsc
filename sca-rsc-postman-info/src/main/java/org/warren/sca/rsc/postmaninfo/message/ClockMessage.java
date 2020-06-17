package org.warren.sca.rsc.postmaninfo.message;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class ClockMessage implements Serializable {

    private int postmanId;

    private Timestamp time;

    private int type;

    public ClockMessage() {
    }

    public ClockMessage(int postmanId, Timestamp time, int type) {
        this.postmanId = postmanId;
        this.time = time;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ClockMessage{" +
                "postmanId=" + postmanId +
                ", time=" + time +
                ", type=" + type +
                '}';
    }
}
