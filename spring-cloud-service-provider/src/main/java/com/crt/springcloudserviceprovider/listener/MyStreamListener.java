package com.crt.springcloudserviceprovider.listener;

import com.crt.springcloudserviceprovider.event.MyApplicationEvent;
import com.crt.springcloudserviceprovider.message.MySink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author Caort
 * @date 2020/10/19 20:30
 */
@EnableBinding(MySink.class)
public class MyStreamListener implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;
    /*@StreamListener(MySink.INPUT)
    public void receive(String message){
        System.out.println(String.format("receive message \'%s\' from %s", message, MySink.INPUT));
    }*/

    @StreamListener(MySink.INPUT)
    public void receive(MyApplicationEvent event){
        System.out.println(String.format("receive message \'%s\' from %s", event.getSource(), MySink.INPUT));
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
