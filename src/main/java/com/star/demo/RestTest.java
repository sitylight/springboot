// Copyright (c) 1998-2018 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2018-04-12, derrick.liang, creation
// ============================================================================
package com.star.demo;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author derrick.liang
 */
public class RestTest {
    public static void main(String[] args) throws IOException {
        String url = "http://192.168.5.132:8080/cpm/7c411b5f-e34a-4e8b-a530-81febc1ed62f";

        ExecutorService service = Executors.newCachedThreadPool();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(20000);
        cm.setDefaultMaxPerRoute(2000);
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization",
                "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSRDIiLCJpYXQiOjE1MjI3NDgwMzN9.hRrKqAxJRBammf3"
                        + "ckEh0htEvjwBk3fmhsM0fGmLKUNI=");
        HttpResponse response = client.execute(httpGet);
        String result = EntityUtils.toString(response.getEntity());

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization",
                "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSRDIiLCJpYXQiOjE1MjI3NDgwMzN9.hRrKqAxJRBammf3"
                        + "ckEh0htEvjwBk3fmhsM0fGmLKUNI=");
        StringEntity stringEntity = new StringEntity(result);
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Content-type", "application/json");
        for(int i =0; i<5000 ; i++) {
            System.out.println("i--- :" + i);
            Runnable runnable = () -> {
                try {
                    HttpResponse postResponse = client.execute(httpPost);
                    if (postResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        System.out.println("update successfully");
                    } else {
                        System.out.println("fail by : " + postResponse.getStatusLine().getReasonPhrase());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            service.submit(runnable);
        }
        service.shutdown();
    }

}
