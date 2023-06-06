package com.pathz.nettychat.controller;

import com.pathz.nettychat.netty.ChatClient;
import javafx.scene.layout.VBox;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

@Component
public class ChatController {

    @FXML
    private Text currentOnline;

    @FXML
    private TextField inputField;

    @FXML
    private Button sendBtn;

    @FXML
    private VBox chatHistory;

    private final ChatClient chatClient;

    public ChatController(@Lazy ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @FXML
    void sendMessage(ActionEvent event) throws InterruptedException {
        chatClient.sendMessage(inputField.getText());
    }

    public Text getCurrentOnline() {
        return currentOnline;
    }

    public VBox getChatHistory() {
        return chatHistory;
    }
}

