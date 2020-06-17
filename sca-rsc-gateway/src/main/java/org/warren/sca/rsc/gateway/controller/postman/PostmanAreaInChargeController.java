package org.warren.sca.rsc.gateway.controller.postman;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.warren.sca.rsc.common.postmaninfo.PostmanAreaInChargeService;
import org.warren.sca.rsc.gateway.vo.ResultVO;

@Api(tags = "快递员管辖区域接口")
@RestController
@RequestMapping("/postman")
public class PostmanAreaInChargeController {

    @Reference
    private PostmanAreaInChargeService postmanAreaInChargeService;


    @ApiOperation("增加管辖区域")
    @PostMapping("areaInChargeAdd")
    public ResultVO areaAdd(@RequestAttribute(value = "postmanId", required = false) int postmanId, int areaId){
        postmanAreaInChargeService.areaInChargeAdd(postmanId, areaId);
        return ResultVO.getSuccess("操作成功");
    }

    @ApiOperation("删除管辖区域")
    @PostMapping("areaInChargeDelete")
    public ResultVO areaDelete(@RequestAttribute(value = "postmanId", required = false) int postmanId, int areaId){
        postmanAreaInChargeService.areaInChargeDelete(postmanId, areaId);
        return ResultVO.getSuccess("操作成功");
    }

}
