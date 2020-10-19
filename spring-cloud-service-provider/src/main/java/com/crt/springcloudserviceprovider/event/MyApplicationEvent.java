package com.crt.springcloudserviceprovider.event;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * @author Caort
 * @date 2020/10/19 21:07
 */
public class MyApplicationEvent extends ApplicationEvent {
    private String from;
    // 后期可以使用DiscoveryClient发送到指定的应用或者实例
    private String to;
    private Date date;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyApplicationEvent(Object source) {
        super(source);
    }

    public MyApplicationEvent(Object source, String from, String to, Date date) {
        super(source);
        this.from = from;
        this.to = to;
        this.date = date;
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
