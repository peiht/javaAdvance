package io.kimmking.rpcfx.client.netty;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        URI uri = new URI("/");
        RpcfxRequest rpcfxRequest = new RpcfxRequest();
        rpcfxRequest.setServiceClass("io.kimmking.rpcfx.demo.api.UserService");
        rpcfxRequest.setMethod("findById");
        Object[] params = new Object[1];
        params[0] = 1992129;
        rpcfxRequest.setParams(params);

        String json = JSON.toJSONString(rpcfxRequest);
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.POST, uri.toASCIIString(),
                Unpooled.wrappedBuffer(json.getBytes(StandardCharsets.UTF_8)));
        request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        request.headers().add(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes())
        .add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        //request.headers().add(HttpHeaderNames)
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        if (msg instanceof ByteBuf) {
            String val = ((ByteBuf) msg).toString(Charset.defaultCharset());
            System.out.println("netty receive : " + val);
        }
        if (msg instanceof FullHttpResponse){
            FullHttpResponse response = (FullHttpResponse) msg;
            ByteBuf buf = response.content();
            String res = buf.toString(CharsetUtil.UTF_8);
            System.out.println("content : " + res);
        }

        AttributeKey<String> key = AttributeKey.valueOf("ServerData");
        ctx.channel().attr(key).set("client done");
        ctx.channel().close();
    }
}
