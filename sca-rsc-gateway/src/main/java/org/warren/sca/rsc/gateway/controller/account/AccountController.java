package org.warren.sca.rsc.gateway.controller.account;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.warren.sca.rsc.common.customerinfo.CustomerBaseInfoService;
import org.warren.sca.rsc.common.postmaninfo.PostmanAccountService;
import org.warren.sca.rsc.gateway.vo.ResultVO;

@Api(tags = "用户登录注册接口")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Reference
    private CustomerBaseInfoService customerBaseInfoService;

    @Reference
    private PostmanAccountService postmanAccountService;

    @ApiOperation("顾客登录")
    @GetMapping("/customer/login")
    public Object customerLogin(String username, String password) {
        String token = customerBaseInfoService.authenticate(username, password);
        return ResultVO.getSuccessWithData("登陆成功", token);
    }

    @ApiOperation("顾客注册")
    @GetMapping("/customer/register")
    public ResultVO customerRegister(String username, String password){
        customerBaseInfoService.register(username, password);
        return ResultVO.getSuccess("注册成功");
    }

    @ApiOperation("快递员登录")
    @GetMapping("/postman/login")
    public Object postmanLogin(String username, String password) {
        String token = postmanAccountService.authenticate(username, password);
        return ResultVO.getSuccessWithData("登陆成功", token);
    }

    @ApiOperation("快递员注册")
    @GetMapping("/postman/register")
    public ResultVO postmanRegister(String username, String password){
        postmanAccountService.register(username, password);
        return ResultVO.getSuccess("注册成功");
    }
}