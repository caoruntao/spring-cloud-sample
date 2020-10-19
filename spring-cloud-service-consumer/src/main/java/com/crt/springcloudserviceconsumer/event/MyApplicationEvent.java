package com.crt.springcloudserviceconsumer.event;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * @author Caort
 * @date 2020/10/19 21:14
 */
public class MyApplicationEvent extends ApplicationEvent {
    private String from = "spring-cloud-service-consumer";
    // 后期可以使用DiscoveryClient发送到指定的应用或者实例
    private String to;
    private final Date date = new Date();

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyApplicationEvent(Object source) {
        super(source);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }
}
