package org.warren.sca.rsc.order.serviceImpl;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


public interface MySource {

    @Output("customer-new-order-output")
    MessageChannel customerNewOrderTopic();

}