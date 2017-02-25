package com.ignaciosuay.integration;

import com.ignaciosuay.message.InputChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Slf4j
@EnableBinding(InputChannel.class)
public class MessageHandler {

    @StreamListener(Sink.INPUT)
    public void handle(String message) {
        log.info("message received {}", message);
        System.out.println(message);
    }
}
