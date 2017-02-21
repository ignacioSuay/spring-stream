package com.ignaciosuay.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MessageResource {

    private final MessageChannel output;

    @RequestMapping(method = RequestMethod.GET, value= "/sendMessage/{message}" )
    public void sendMessage(@PathVariable String message){
        log.info(message);
        Message<String> msg = MessageBuilder.withPayload(message).build();
        output.send(msg);
    }
}
