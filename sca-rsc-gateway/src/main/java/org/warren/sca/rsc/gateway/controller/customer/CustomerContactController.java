package org.warren.sca.rsc.gateway.controller.customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.warren.sca.rsc.common.customerinfo.CustomerAreaService;
import org.warren.sca.rsc.common.customerinfo.CustomerContactService;
import org.warren.sca.rsc.common.customerinfo.dto.ContactDTO;
import org.warren.sca.rsc.common.exception.CheckedException;
import org.warren.sca.rsc.gateway.vo.ContactVO;
import org.warren.sca.rsc.gateway.vo.ResultVO;

import java.util.List;
import java.util.stream.Collectors;

@Api("顾客收货地址接口")
@RestController
@RequestMapping("customer/contact")
public class CustomerContactController {

    @Reference
    private CustomerContactService customerContactService;

    @Reference
    private CustomerAreaService customerAreaService;

    @ApiOperation("获取所有收货地址")
    @GetMapping("lists")
    public ResultVO getContacts(@RequestAttribute(value = "customerId", required = false) Integer customerId ){
        List<ContactVO> vos = customerContactService.getContactsByCustomerId(customerId)
                .stream()
                .map(dto -> {
                    ContactVO vo  = new ContactVO();
                    BeanUtils.copyProperties(dto, vo);
                    vo.setAddressString(customerAreaService.getAddressStringByAreaId(dto.getAreaId())+" "+dto.getDetail());
                    return vo;
                })
                .collect(Collectors.toList());
        return ResultVO.getSuccessWithData("操作成功",vos);
    }

    @ApiOperation("删除收货地址")
    @GetMapping("delete")
    public ResultVO getContacts(@RequestAttribute(value = "customerId", required = false) Integer customerId, int contactId ){
        ContactDTO contact = customerContactService.getContactByContactId(contactId);
        if (contact.getCustomerId() != customerId)
            throw new CheckedException("操作非法，该收货地址不属于你");
        customerContactService.deleteContactById(contactId);
        return ResultVO.getSuccessVO();
    }

}
