package org.geetbang.sun_homework.No2.httpclient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.geetbang.sun_homework.No2.filter.NettyHttpFilter;
import org.geetbang.sun_homework.No2.filter.impl.NettyHttpResponseFilter;

import java.net.URL;
import java.util.Map;

/**
 * 通过HttpClient调用socket写的http请求
 */
public class CallSocketServer {

    public static final String URL = "http://localhost:8801";

    private CloseableHttpAsyncClient httpclient;

    public String queryData(FullHttpRequest fullRequest, final ChannelHandlerContext ctx, Map<String,String> routes){
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

        return this.queryDataByPost(fullRequest,ctx,routes);
    }


    public String queryDataByPost(FullHttpRequest fullRequest,final ChannelHandlerContext ctx, Map<String,String> routes) {

        String uri = fullRequest.uri();
        String requestUrl = null;
        for(String key : routes.keySet()){
            if(uri.endsWith(key)){
                requestUrl = routes.get(key);
                break;
            }
        }
        if(null == requestUrl || "".equals(requestUrl)){
            return null;
        }

        final HttpPost httpPost = new HttpPost(requestUrl);
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
