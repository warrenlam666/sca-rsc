package org.warren.sca.rsc.gateway.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO implements Serializable {

    private boolean success;
    private String msg;
    private Object data;

    ResultVO(boolean success, String msg, Object data){
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    ResultVO(boolean success, String msg){
        this.success = success;
        this.msg = msg;
    }

    public static ResultVO getSuccess(String msg){
        return new  ResultVO(true, msg);
    }

    public static ResultVO getSuccessVO(){
        return new  ResultVO(true, "操作成功");
    }

    public static ResultVO getFailure(String msg){
        return new ResultVO(false, msg);
    }

    public static ResultVO getFailureVO(){
        return new ResultVO(false, "操作失败");
    }

    public static ResultVO getSuccessWithData(String msg, Object data){
        return new ResultVO(true, msg, data);
    }

}

