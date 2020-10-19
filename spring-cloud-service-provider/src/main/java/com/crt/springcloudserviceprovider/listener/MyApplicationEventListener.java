package com.crt.springcloudserviceprovider.listener;

import com.crt.springcloudserviceprovider.event.MyApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Caort
 * @date 2020/10/19 21:51
 */
@Component
public class MyApplicationEventListener  {
    @EventListener
    @Async
    public void onEvent(MyApplicationEvent event){
        System.out.println(String.format("receive event source \'%s\' from '\'%s\' at \'%s\'", event.getSource(), event.getFrom(), event.getDate()));
    }
}
