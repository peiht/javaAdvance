package io.kimmking.rpcfx.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;

/**
 * @author hitopei
 * netty 实现请求rpc
 */
public class NettyClient {

    public static void main(String[] args) {
        Bootstrap client = new Bootstrap();

        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group).remoteAddress("localhost", 8080);
        client.channel(NioSocketChannel.class);
        client.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(new HttpClientCodec());
                ch.pipeline().addLast(new HttpObjectAggregator(65536));
                ch.pipeline().addLast(new HttpContentDecompressor());
                ch.pipeline().addLast(new ClientHandler());
            }
        });

        try {
            ChannelFuture future = client.connect().sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
