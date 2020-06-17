package org.warren.sca.rsc.gateway.controller.postman;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.warren.sca.rsc.common.distribution.DistributionOrderPoolService;
import org.warren.sca.rsc.common.postmaninfo.PostmanAreaInChargeService;
import org.warren.sca.rsc.common.postmaninfo.PostmanClockService;
import org.warren.sca.rsc.common.postmaninfo.PostmanWorkService;
import org.warren.sca.rsc.gateway.vo.ResultVO;

@Api(tags = "快递员相关接口")
@RestController
@RequestMapping("/postman")
public class PostmanController {

    @Reference
    private PostmanAreaInChargeService postmanAreaInChargeService;

    @Reference
    private PostmanClockService postmanClockService;

    @Reference
    private DistributionOrderPoolService distributionOrderPoolService;

    @Reference
    private PostmanWorkService postmanWorkService;

    @ApiOperation("开启自动接单模式")
    @PostMapping("openAROM")
    public ResultVO openAROM(@RequestAttribute(value = "postmanId", required = false) int postmanId){
        postmanAreaInChargeService.openAROM(postmanId);
        return ResultVO.getSuccess("操作成功");
    }

    @ApiOperation("关闭自动接单模式")
    @PostMapping("closerAROM")
    public ResultVO closerAROM(@RequestAttribute(value = "postmanId", required = false) int postmanId){
        postmanAreaInChargeService.closeAROM(postmanId);
        return ResultVO.getSuccess("操作成功");
    }

    @ApiOperation("上班打卡")
    @PostMapping("clockIn")
    public ResultVO clockIn(@RequestAttribute(value = "postmanId", required = false) int postmanId){
        postmanClockService.clockIn(postmanId);
        return ResultVO.getSuccess("操作成功");
    }

    @ApiOperation("下班退卡")
    @PostMapping("clockOut")
    public ResultVO clockOut(@RequestAttribute(value = "postmanId", required = false) int postmanId){
        postmanClockService.clockOut(postmanId);
        return ResultVO.getSuccess("操作成功");
    }

    @ApiOperation("获取订单池订单")
    @PostMapping("listOrderPool")
    public ResultVO listOrderPool(){
        return ResultVO.getSuccessWithData("操作成功", distributionOrderPoolService.getOrders());
    }

    @ApiOperation("从订单池抢单")
    @PostMapping("acquiredOrder")
    public ResultVO acquiredOrder(@RequestAttribute(value = "postmanId", required = false) int postmanId, long orderId){
        boolean res = distributionOrderPoolService.acquireOrder(postmanId, orderId);
        return res ? ResultVO.getSuccessVO() : ResultVO.getFailure("status错误");
    }

}
