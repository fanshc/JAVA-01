package org.geetbang.sun_homework.No2.filter.impl;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.geetbang.sun_homework.No2.filter.NettyHttpFilter;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

/**
 * @Description: 实现的http拦截器。暂时的实现类是通过new创建出来的
 * 后续完善的时候，要通过实现接口，动态获取实现类
 * 所有实现这个接口的实现类都可以对头啊， 返回值进行处理
 * @Auther: fanshc
 * @Date: 2021/01/31/1:25 上午
 */
public class NettyHttpResponseFilter implements NettyHttpFilter {

    @Override
    public void filter(HttpResponse fullResponse, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try{
            String result = EntityUtils.toString(fullResponse.getEntity());
            result = "http交互中增加过滤器 ： " + result;
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK, Unpooled.wrappedBuffer(result.getBytes()));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", result.getBytes().length);
        }catch(Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        } finally {
            ctx.write(response);
            ctx.flush();
        }
    }
}
