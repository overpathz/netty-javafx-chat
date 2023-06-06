package com.pathz.nettychat.handler;

import com.pathz.nettychat.controller.ChatController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OnlineMessageSentHandler {
    private final ChatController controller;

    public void handle(String message) {
        controller.getCurrentOnline().setText(message);
    }
}
