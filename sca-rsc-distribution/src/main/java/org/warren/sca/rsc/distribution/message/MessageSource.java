package org.warren.sca.rsc.distribution.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageSource {

    String CUSTOMER_NEW_ORDER_INPUT = "customer-new-order-input";

    String CUSTOMER_OLD_ORDER_INPUT = "customer-old-order-input";


    @Input(CUSTOMER_NEW_ORDER_INPUT)
    SubscribableChannel customerNewOrderInput();

    @Input(CUSTOMER_OLD_ORDER_INPUT)
    SubscribableChannel customerOldOrderInput();

}
