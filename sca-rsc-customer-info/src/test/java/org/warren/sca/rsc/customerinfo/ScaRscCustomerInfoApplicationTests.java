package org.warren.sca.rsc.customerinfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.warren.sca.rsc.customerinfo.pojo.po.CustomerDO;
import org.warren.sca.rsc.customerinfo.mapper.CustomerMapper;

import java.util.List;

@SpringBootTest
class ScaRscCustomerInfoApplicationTests {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void contextLoads() {
        List<CustomerDO> list = customerMapper.selectList(null);
        System.out.println(list);
    }

}
