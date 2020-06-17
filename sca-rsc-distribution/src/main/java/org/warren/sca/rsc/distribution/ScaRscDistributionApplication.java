package org.warren.sca.rsc.distribution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.warren.sca.rsc.distribution.message.MessageSource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({MessageSource.class})
public class ScaRscDistributionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaRscDistributionApplication.class, args);
    }

}
