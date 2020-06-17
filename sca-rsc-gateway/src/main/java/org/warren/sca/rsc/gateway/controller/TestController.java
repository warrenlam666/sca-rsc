package org.warren.sca.rsc.gateway.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.warren.sca.rsc.common.test.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Reference
    private TestService testService;

    @GetMapping("test")
    public void test(){
        testService.test();
    }

}
