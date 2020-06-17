package org.warren.sca.rsc.customerinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
@MapperScan("org.warren.sca.rsc.customerinfo.mapper")
@EnableDiscoveryClient
public class ScaRscCustomerInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaRscCustomerInfoApplication.class, args);
    }

}
