package com.pathz.nettychat.service;

import com.pathz.nettychat.exception.UnableLoadFxmlException;
import com.pathz.nettychat.util.StageHolder;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SceneChanger {
    private final StageHolder stageHolder;
    private final ApplicationContext context;

    public void changeScene(Resource resourcePage) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(resourcePage.getURL());
            fxmlLoader.setControllerFactory(context::getBean);
            final Parent parent = fxmlLoader.load();
            final Scene scene = new Scene(parent);
            Platform.runLater(() -> stageHolder.getStage().setScene(scene));
        } catch (IOException e) {
            throw new UnableLoadFxmlException(resourcePage, e);
        }
    }
}
