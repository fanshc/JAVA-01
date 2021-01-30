package org.geetbang.sun_homework.No2.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.geetbang.sun_homework.No2.httpclient.CallSocketServer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: fanshc
 * @Date: 2021/01/31/12:42 上午
 */
public class NettyHttpHandler extends ChannelInboundHandlerAdapter {

    private static final Map<String,String> routes = new HashMap<>();;

    static {
        routes.put("test","http://127.0.0.1:9090/");
        routes.put("test1","http://127.0.0.1:9091/");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullRequest = (FullHttpRequest) msg;
        String uri = fullRequest.uri();
        handlerTest(fullRequest, ctx);

    }
    private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            CallSocketServer server = new CallSocketServer();
            server.queryData(fullRequest,ctx,routes);
        } catch (Exception e) {
            System.out.println("处理出错:"+e.getMessage());
        }
    }

}
