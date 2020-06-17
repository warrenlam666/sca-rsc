package org.warren.sca.rsc.common.postmaninfo;

public interface PostmanAreaInChargeService {

    void areaInChargeAdd(int postman, int areaId);

    void areaInChargeDelete(int postman, int areaId);

    void openAROM(int PostmanId);   //开启自动接单模式

    void closeAROM(int PostmanId);    //关闭自动接单模式

}
