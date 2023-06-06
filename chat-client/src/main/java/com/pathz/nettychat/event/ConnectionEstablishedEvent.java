package com.pathz.nettychat.event;

import org.springframework.context.ApplicationEvent;

public class ConnectionEstablishedEvent extends ApplicationEvent {
    public ConnectionEstablishedEvent(Object source) {
        super(source);
    }
}
