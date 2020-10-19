package com.crt.springcloudserviceprovider.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {
    String INPUT = "sink_input";

    @Input(INPUT)
    SubscribableChannel input();
}
