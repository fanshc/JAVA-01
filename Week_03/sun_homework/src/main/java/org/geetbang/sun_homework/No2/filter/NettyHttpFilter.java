package org.geetbang.sun_homework.No2.filter;

import io.netty.channel.ChannelHandlerContext;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

/**
 * @Description:
 * @Auther: fanshc
 * @Date: 2021/01/31/1:16 上午
 */
public interface NettyHttpFilter {

    default void filter(HttpRequest fullRequest, ChannelHandlerContext ctx){
    }
    default void filter(HttpResponse fullResponse, ChannelHandlerContext ctx){
    }
}
