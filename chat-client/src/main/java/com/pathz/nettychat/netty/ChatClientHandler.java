package com.pathz.nettychat.netty;

import com.pathz.nettychat.event.ConnectionEstablishedEvent;
import com.pathz.nettychat.handler.OnlineMessageSentHandler;
import com.pathz.nettychat.handler.ServerMessageSentHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

    private final ConfigurableApplicationContext context;
    private final OnlineMessageSentHandler onlineMessageSentHandler;
    private final ServerMessageSentHandler serverMessageSentHandler;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        if (msg.contains("Online")) {
            String online = msg.split("\\.")[0].split("=")[1];
            onlineMessageSentHandler.handle(online);
        } else {
            log.info(ctx.toString());
            serverMessageSentHandler.handle(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        context.publishEvent(new ConnectionEstablishedEvent(this));
        Channel channel = ctx.channel();
    }
}
