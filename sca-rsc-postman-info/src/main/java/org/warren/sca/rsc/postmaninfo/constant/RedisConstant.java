package org.warren.sca.rsc.postmaninfo.constant;

public class RedisConstant {

    private static String PostmanWorkload = "postman_workload_";

    private static String POSTMAN_OF_AREA = "POSTMAN_OF_AREA_";

    public static String getPostmanWorkload(int pid){
        return PostmanWorkload+ pid;
    }

    public static String getPostmanOfArea(int aid){
        return POSTMAN_OF_AREA+aid;
    }

}
