package org.warren.sca.rsc.gateway.controller.postman;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.warren.sca.rsc.common.order.OrderDetailService;
import org.warren.sca.rsc.common.order.OrderInfoService;
import org.warren.sca.rsc.common.order.OrderMasterService;
import org.warren.sca.rsc.common.order.vo.TaskInfoVO;
import org.warren.sca.rsc.common.postmaninfo.PostmanWorkService;
import org.warren.sca.rsc.common.postmaninfo.constant.PostmanWorkType;
import org.warren.sca.rsc.gateway.vo.ResultVO;

@Api(tags = "快递员任务接口")
@RestController
@RequestMapping("/postman/task")
public class PostmanWorkController {

    @Reference
    private PostmanWorkService postmanWorkService;

    @Reference
    private OrderMasterService orderMasterService;

    @Reference
    private OrderDetailService orderDetailService;

    @Reference OrderInfoService orderInfoService;

    @ApiOperation("获取所有任务列表")
    @GetMapping("listTasks")
    public ResultVO listTasks(@RequestAttribute(value = "postmanId", required = false) int postmanId){
        return ResultVO.getSuccessWithData(
                "操作成功",
                postmanWorkService.listAllTask(postmanId, 0, 0)
        );
    }

    @ApiOperation("获取任务信息")
    @GetMapping("getTaskDetail")
    public ResultVO getTaskDetail(int orderId){
        return ResultVO.getSuccessWithData(
                "操作成功",
                new TaskInfoVO(
                        orderMasterService.getOrderMasterById(orderId),
                        orderDetailService.getOrderDetails(orderId)
                )
        );
    }

    @ApiOperation("获取未完成任务列表")
    @GetMapping("listUnDoneTasks")
    public ResultVO listUnDoneTasks(@RequestAttribute(value = "postmanId", required = false) int postmanId){
        return ResultVO.getSuccessWithData(
                "操作成功",
                postmanWorkService.listAllUnDoneTask(postmanId)
        );
    }

    @ApiOperation("任务完成")
    @PostMapping("finishTask")
    public ResultVO finishTask(@RequestAttribute(value = "postmanId", required = false) int postmanId, int status, long orderId){
        if (status == PostmanWorkType.PICK_UP)
            postmanWorkService.finishPickUpTask(postmanId, orderId);
        else if (status == PostmanWorkType.DISPATCHER)
            postmanWorkService.finishDistributionTask(postmanId, orderId);
        else
            return ResultVO.getFailure("status错误");
        return ResultVO.getSuccessVO();
    }

    @ApiOperation("获取订单详情")
    @GetMapping("details")
    public ResultVO details(@RequestAttribute(value = "postmanId", required = false) int postmanId, String orderId){
        long oid = Long.parseLong(orderId);
        return ResultVO.getSuccessWithData("操作成功",  orderInfoService.getOrderInfo(oid));
    }
}
