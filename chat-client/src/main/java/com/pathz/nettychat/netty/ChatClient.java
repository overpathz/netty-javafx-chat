package com.pathz.nettychat.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatClient {

    @Value(value = "${spring.application.server.ip}")
    private String host;

    @Value(value = "${spring.application.server.port}")
    private int port;
    private String clientName;
    private ChannelFuture channelFuture;
    private final ChatClientHandler chatClientHandler;

    public void startClientServer(String clientName) throws Exception {
        this.clientName = clientName;

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new StringDecoder());
                            p.addLast(new StringEncoder());
                            p.addLast(chatClientHandler);
                        }
                    });

            ChannelFuture f = b.connect(host, port).sync();
            channelFuture = f;
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
            System.out.println("graceful shutdown");
        }
    }

    public void sendMessage(String message) throws InterruptedException {
        Channel channel = channelFuture.sync().channel();
        channel.writeAndFlush("[" + clientName + "]: " + message);
        channel.flush();
    }
}
