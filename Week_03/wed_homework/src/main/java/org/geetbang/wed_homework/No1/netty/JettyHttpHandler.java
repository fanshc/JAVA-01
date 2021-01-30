package org.geetbang.wed_homework.No1.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import org.geetbang.wed_homework.No1.httpclient.CallSocketServer;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Description:
 * @Auther: fanshc
 * @Date: 2021/01/31/12:42 上午
 */
public class JettyHttpHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        super.channelReadComplete(ctx);
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            if (uri.contains("/test")) {
                handlerTest(fullRequest, ctx);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
    private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            CallSocketServer server = new CallSocketServer();
            String result = server.queryData();

            response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(result.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
        } catch (Exception e) {
            System.out.println("处理出错:"+e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderNames.KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

}
