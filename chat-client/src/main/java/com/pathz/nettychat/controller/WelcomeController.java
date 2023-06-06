package com.pathz.nettychat.controller;

import com.pathz.nettychat.netty.ChatClient;
import com.pathz.nettychat.service.SceneChanger;
import com.pathz.nettychat.util.StageHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class WelcomeController {
    private final SceneChanger sceneChanger;
    private final StageHolder stageHolder;
    private final ChatClient chatClient;

    @Value("classpath:/scene/waiting.fxml")
    private Resource waitingPage;

    @FXML
    private Button joinChatBtn;

    @FXML
    private TextField usernameInput;

    @FXML
    private Text errorMessage;

    private final ExecutorService taskExecutor = Executors.newSingleThreadExecutor();

    @FXML
    void initialize() {

    }

    @FXML
    void joinChat() {
        if (isValidUsername()) {
            taskExecutor.submit(() -> initializeNettyClient(usernameInput.getText()));
            sceneChanger.changeScene(waitingPage);
        }
    }

    private void initializeNettyClient(String text) {
        try {
            chatClient.startClientServer(text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isValidUsername() {
        String username = usernameInput.getText();
        if (username == null || username.length() < 6 || username.isBlank()) {
            showErrorWithMessage("Provide username. Min 6 symbols.");
            return false;
        }
        return true;
    }

    private void showErrorWithMessage(String message) {
        taskExecutor.execute(()-> {
            joinChatBtn.setVisible(false);
            errorMessage.setText(message);
            errorMessage.setVisible(true);
            errorMessage.setDisable(false);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            errorMessage.setVisible(false);
            errorMessage.setDisable(true);
            joinChatBtn.setVisible(true);
        });
    }
}
