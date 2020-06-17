package org.warren.sca.rsc.postmaninfo.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OldOrderSource {

    @Output("customer-old-order-output")
    MessageChannel oldOrderOutput();

}
