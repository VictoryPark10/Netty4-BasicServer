package com.vicsoft.server.init;

import com.vicsoft.server.Global;
import com.vicsoft.server.handler.BasicServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

public class BasicServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new ReadTimeoutHandler(Global.READ_TIMEOUT_SECONDS));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new IdleStateHandler(Global.READ_TIMEOUT_SECONDS, 0, 0, TimeUnit.SECONDS));
        pipeline.addLast(new BasicServerHandler());
    }

}
