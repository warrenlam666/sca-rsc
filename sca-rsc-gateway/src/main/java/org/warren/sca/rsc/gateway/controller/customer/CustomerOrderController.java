package org.warren.sca.rsc.gateway.controller.customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.warren.sca.rsc.common.order.OrderInfoService;
import org.warren.sca.rsc.common.order.OrderMasterService;
import org.warren.sca.rsc.common.order.dto.CustomerOrderInfoDTO;
import org.warren.sca.rsc.common.order.dto.OrderCreateDTO;
import org.warren.sca.rsc.gateway.vo.ResultVO;

@Api(tags = "顾客订单接口")
@RestController
@RequestMapping("customer/order")
public class CustomerOrderController {

    @Reference
    private OrderMasterService orderMasterService;

    @Reference
    private OrderInfoService orderInfoService;

    @ApiOperation("创建订单")
    @PostMapping("create")
    public ResultVO generateOrder(OrderCreateDTO orderCreateDTO, @RequestAttribute(value = "customerId", required = false) Integer customerId ){
        orderMasterService.create(orderCreateDTO, customerId);
        return ResultVO.getSuccess("下单成功");
    }

    @ApiOperation("获取订单信息")
    @GetMapping("details")
    public ResultVO details(@RequestAttribute(value = "customerId", required = false) Integer customerId, String orderId){
        long oid = Long.parseLong(orderId); //兼容前端js不支持long
        CustomerOrderInfoDTO dto = orderInfoService.getOrderInfo(oid);
        if (dto.getMasterDTO().getCustomerId() != customerId)
            return ResultVO.getFailureVO();     //安全检验，顾客只能查看自己的车订单信息
        return ResultVO.getSuccessWithData("操作成功", dto);
    }

    @ApiOperation("获取所有订单Id")
    @GetMapping("ids")
    public ResultVO orderIds(@RequestAttribute(value = "customerId", required = false) Integer customerId){
        return ResultVO.getSuccessWithData("操作成功", orderMasterService.getIdsByCustomerId(customerId));
    }

}
