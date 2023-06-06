package com.pathz.nettychat.handler;

import com.pathz.nettychat.event.StageReadyEvent;
import com.pathz.nettychat.util.StageHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StageHolderSetter implements ApplicationListener<StageReadyEvent> {
    private final StageHolder stageHolder;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        stageHolder.setStage(event.getStage());
    }
}
