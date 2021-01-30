package org.geetbang.sun_homework.No1.httpclient;

import io.netty.channel.ChannelHandlerContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.geetbang.sun_homework.No1.filter.NettyHttpFilter;
import org.geetbang.sun_homework.No1.filter.impl.NettyHttpResponseFilter;

/**
 * 通过HttpClient调用socket写的http请求
 */
public class CallSocketServer {

    public static final String URL = "http://localhost:8801";

    private CloseableHttpAsyncClient httpclient;

    public String queryData(final ChannelHandlerContext ctx){
        if (httpclient == null) {
            int cores = Runtime.getRuntime().availableProcessors();
            IOReactorConfig ioConfig = IOReactorConfig.custom()
                    .setConnectTimeout(1000)
                    .setSoTimeout(1000)
                    .setIoThreadCount(cores)
                    .setRcvBufSize(32 * 1024)
                    .build();
            httpclient = HttpAsyncClients.custom().setMaxConnTotal(40)
                    .setMaxConnPerRoute(8)
                    .setDefaultIOReactorConfig(ioConfig)
                    .setKeepAliveStrategy((response,context) -> 6000)
                    .build();
            httpclient.start();
        }

        return this.queryDataByPost(ctx);
    }


    public String queryDataByPost( final ChannelHandlerContext ctx) {

        final HttpPost httpPost = new HttpPost(URL);
        StringEntity entity = new StringEntity("", "UTF-8");
        httpPost.setEntity(entity);

        final String JSON_TYPE = "application/json;charset=UTF-8";
        httpPost.setHeader(HTTP.CONTENT_TYPE, JSON_TYPE);

        httpclient.execute(httpPost, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                NettyHttpFilter filter = new NettyHttpResponseFilter();
                filter.filter(httpResponse,ctx);
            }

            @Override
            public void failed(Exception e) {
                httpPost.abort();
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
                httpPost.abort();
            }
        });
        return null;

    }

}
