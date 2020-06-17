package org.warren.sca.rsc.postmaninfo.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@TableName("area_in_charge")
public class AreaInChargePO implements Serializable {

    @TableId(type = IdType.AUTO)
    private int id;

    private int postmanId;

    private int areaId;

    public AreaInChargePO() {
    }

    public AreaInChargePO(int id, int postmanId, int areaId) {
        this.id = id;
        this.postmanId = postmanId;
        this.areaId = areaId;
    }

    public AreaInChargePO(int postmanId, int areaId) {
        this.postmanId = postmanId;
        this.areaId = areaId;
    }
}
