package com.pathz.nettychat.handler;

import com.pathz.nettychat.controller.ChatController;
import javafx.application.Platform;
import javafx.scene.text.Text;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServerMessageSentHandler {
    private final ChatController controller;

    public void handle(String message) {
        Platform.runLater(() -> controller.getChatHistory().getChildren().add(new Text(message)));
    }
}