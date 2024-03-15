package com.ybj.rabbit.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface PulsarBindings {

    String OUTPUT = "pulsarOutput";
    String INPUT = "pulsarInput";

    @Output(OUTPUT)
    MessageChannel pulsarOutput();

    @Input(INPUT)
    SubscribableChannel pulsarInput();
}
