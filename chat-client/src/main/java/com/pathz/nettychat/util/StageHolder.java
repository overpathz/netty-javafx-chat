package com.pathz.nettychat.util;

import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class StageHolder {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
