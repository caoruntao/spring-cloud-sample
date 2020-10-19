package com.crt.springcloudserviceconsumer.listener;

import com.crt.springcloudserviceconsumer.event.MyApplicationEvent;
import com.crt.springcloudserviceconsumer.message.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Caort
 * @date 2020/10/19 21:44
 *
 * Spring中每个ApplicationListener只监听一种事件，想要一个ApplicationListener监听多种事件，可以使用SmartApplicationListener
 */
@Configuration
public class MyApplicationEventListener implements SmartApplicationListener {
    @Autowired
    private MySource mySource;

    @Autowired
    @Qualifier(MySource.OUTPUT)
    private MessageChannel messageChannel;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        if(eventType.isAssignableFrom(MyApplicationEvent.class)){
            return true;
        }
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        MyApplicationEvent myApplicationEvent = (MyApplicationEvent) event;
        messageChannel.send(MessageBuilder.withPayload(myApplicationEvent).build());
    }
}
