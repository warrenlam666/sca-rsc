package org.warren.sca.rsc.distribution.serviceImpl;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.warren.sca.rsc.common.customerinfo.CustomerContactService;
import org.warren.sca.rsc.common.order.OrderMasterService;
import org.warren.sca.rsc.common.order.OrderDetailService;
import org.warren.sca.rsc.common.postmaninfo.PostmanWorkService;
import org.warren.sca.rsc.common.test.TestService;

@Service
public class TestServiceImpl implements TestService {

    @Reference
    private CustomerContactService customerContactService;

    @Reference
    private OrderMasterService orderBaseService;

    @Reference
    private OrderDetailService orderDetailService;

    @Reference
    private PostmanWorkService postmanWorkService;

    @Override
    @GlobalTransactional
    public void test() {
        System.out.println("xid: "+ RootContext.getXID());
        orderDetailService.updateToDDP(123, 2);
        postmanWorkService.addPickUpTask(123, 1);
    }
}
