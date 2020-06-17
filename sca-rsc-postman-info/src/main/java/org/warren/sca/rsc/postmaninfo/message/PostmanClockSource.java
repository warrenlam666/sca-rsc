package org.warren.sca.rsc.postmaninfo.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface PostmanClockSource {

    String POSTMAN_CLOCK_INPUT = "postman-clock-input";

    @Input(POSTMAN_CLOCK_INPUT)
    SubscribableChannel postmanClockInput();

    @Output("postman-clock-output")
    MessageChannel postmanClockOutput();

}
