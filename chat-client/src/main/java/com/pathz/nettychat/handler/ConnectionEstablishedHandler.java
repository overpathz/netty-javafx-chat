package com.pathz.nettychat.handler;

import com.pathz.nettychat.event.ConnectionEstablishedEvent;
import com.pathz.nettychat.service.SceneChanger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConnectionEstablishedHandler implements ApplicationListener<ConnectionEstablishedEvent> {
    private final SceneChanger sceneChanger;

    @Value("classpath:/scene/chat.fxml")
    private Resource chatPage;

    @Override
    public void onApplicationEvent(ConnectionEstablishedEvent event) {
        sceneChanger.changeScene(chatPage);
    }
}
