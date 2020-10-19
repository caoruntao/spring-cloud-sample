package com.crt.springcloudserviceconsumer.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {
    String OUTPUT = "sink_output";

    @Output(OUTPUT)
    MessageChannel output();
}
