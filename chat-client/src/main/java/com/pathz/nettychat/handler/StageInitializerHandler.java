package com.pathz.nettychat.handler;

import com.pathz.nettychat.event.StageReadyEvent;
import com.pathz.nettychat.exception.UnableLoadFxmlException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class StageInitializerHandler implements ApplicationListener<StageReadyEvent> {
    @Value("classpath:/scene/welcome.fxml")
    private Resource welcomePage;

    @Value("${spring.application.ui.title}")
    private String applicationTitle;
    private final ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(welcomePage.getURL());
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent parent = fxmlLoader.load();
            Stage stage = event.getStage();
            stage.setScene(new Scene(parent, 400, 300));
            stage.setTitle(applicationTitle);
            stage.show();
        } catch (IOException e) {
            throw new UnableLoadFxmlException(welcomePage, e);
        }
    }
}
