package com.vicsoft.server.server;

import com.vicsoft.server.Global;
import com.vicsoft.server.init.BasicServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicServer {

    public static BasicServer getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final BasicServer INSTANCE = new BasicServer();
    }

    public void openChannel() {
        log.info("Open basic server channel..");

        EventLoopGroup parentGroup = new NioEventLoopGroup(1);
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(parentGroup, childGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new BasicServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(Global.MY_PORT).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException ie) {
            log.error("Exception occurred while bind server: {}", ie.getMessage(), ie);
        }
    }

}
