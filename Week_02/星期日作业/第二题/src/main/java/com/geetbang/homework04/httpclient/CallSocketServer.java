package com.geetbang.homework04.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 通过HttpClient调用socket写的http请求
 */
public class CallSocketServer {

    public static final String URL = "http://localhost:8801";

    public static void main(String[] args) {

        CloseableHttpClient closeableHttpClient =  HttpClients.createDefault();
        System.out.println("get请求"+CallSocketServer.queryDataByGet(closeableHttpClient));


        System.out.println("Post请求"+CallSocketServer.queryDataByPost(closeableHttpClient));

    }

    public static String queryDataByGet(CloseableHttpClient closeableHttpClient) {
        HttpGet httpGet = new HttpGet(URL);
        CloseableHttpResponse response = null;
        try {
            try{
                response = closeableHttpClient.execute(httpGet);
                HttpEntity entity1 = response.getEntity();
                return EntityUtils.toString(entity1, "UTF-8");
            } finally {
                if(response != null){
                    response.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    public static String queryDataByPost(CloseableHttpClient closeableHttpClient) {

        HttpPost httpPost = new HttpPost(URL);
        StringEntity entity = new StringEntity("", "UTF-8");
        httpPost.setEntity(entity);

        final String JSON_TYPE = "application/json;charset=UTF-8";
        httpPost.setHeader(HTTP.CONTENT_TYPE, JSON_TYPE);

        CloseableHttpResponse response = null;
        try{
            try {
                response = closeableHttpClient.execute(httpPost);
                System.out.println(response.getStatusLine());
                HttpEntity resultEntity = response.getEntity();
                return EntityUtils.toString(resultEntity, "UTF-8");
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
