package org.warren.sca.rsc.postmaninfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.warren.sca.rsc.postmaninfo.message.OldOrderSource;
import org.warren.sca.rsc.postmaninfo.message.PostmanClockSource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({PostmanClockSource.class, OldOrderSource.class})
@MapperScan("org.warren.sca.rsc.postmaninfo.mapper")
public class ScaRscPostmanInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaRscPostmanInfoApplication.class, args);
    }

}
