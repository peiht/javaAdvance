package io.kimmking.rpcfx.client.netty;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.client.cache.NettyCache;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;

/**
 * @author hitopei
 * netty 实现请求rpc
 */
public class NettyClient {

    /**
     * rpc 请求
     */
    private final RpcfxRequest request;
    /**
     * 请求路径
     */
    private final String url;
    /**
     * 请求host
     */
    private final String host;
    /**
     * 请求端口
     */
    private final int port;

    private SocketChannel socketChannel;

    public NettyClient(RpcfxRequest request, String url, String host, int port) {
        this.host = host;
        this.url = url;
        this.port = port;
        this.request = request;
    }

    /**
     *
     * @return res
     */
    public String doRequest(){
        String result = "";
        Bootstrap client = new Bootstrap();

        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group).remoteAddress(host, port);
        client.channel(NioSocketChannel.class);
        client.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch){
                ch.pipeline().addLast(new HttpClientCodec());
                ch.pipeline().addLast(new HttpObjectAggregator(65536));
                ch.pipeline().addLast(new HttpContentDecompressor());
                ch.pipeline().addLast(new ClientHandler(request, url));
            }
        });

        try {
            ChannelFuture future = client.connect().sync();
            System.out.println("future is done ? " + future.isDone());
            if (future.isDone()) {
                result = NettyCache.get(JSON.toJSONString(request));
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "";
        } finally {
            group.shutdownGracefully();
        }
        return result;
    }

    public static void main(String[] args) {
        RpcfxRequest rpcfxRequest = new RpcfxRequest();
        rpcfxRequest.setServiceClass("io.kimmking.rpcfx.demo.api.UserService");
        rpcfxRequest.setMethod("findById");
        Object[] params = new Object[1];
        params[0] = 1992129;
        rpcfxRequest.setParams(params);
        NettyClient client = new NettyClient(rpcfxRequest, "/", "localhost", 8080);
        System.out.println(client.doRequest());
    }
}
