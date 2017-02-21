package com.ignaciosuay.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputChannel {

    @Output
    MessageChannel output();
}
