package org.warren.sca.rsc.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.warren.sca.rsc.order.serviceImpl.MySource;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("org.warren.sca.rsc.order.mapper")
@EnableBinding({MySource.class})
public class ScaRscOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaRscOrderApplication.class, args);
    }

}
