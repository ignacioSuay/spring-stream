package com.ignaciosuay.integration;

import com.ignaciosuay.message.InputChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
public class ManageMessage {

    @Bean
    IntegrationFlow messageFlow(InputChannel channels) {
        return IntegrationFlows
                .from(channels.input())
                .handle(String.class, (payload, headers) -> {
                    System.out.println(payload);
                    return null;
                }).get();
    }

}
