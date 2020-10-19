package com.crt.springcloudserviceconsumer.controller;

import com.crt.springcloudserviceconsumer.event.MyApplicationEvent;
import com.crt.springcloudserviceconsumer.message.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Caort
 * @date 2020/10/19 20:18
 */
@RestController
@EnableBinding(MySource.class)
public class MessageController implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private MySource mySource;

    @Autowired
    @Qualifier(MySource.OUTPUT)
    private MessageChannel messageChannel;

    @GetMapping("/send/{message}")
    public void send(@PathVariable String message){
        messageChannel.send(MessageBuilder.withPayload(message).build());
    }

    @GetMapping("/event/{message}")
    public void sendEvent(@PathVariable String message){
        MyApplicationEvent myApplicationEvent = new MyApplicationEvent(message);
        applicationEventPublisher.publishEvent(myApplicationEvent);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
