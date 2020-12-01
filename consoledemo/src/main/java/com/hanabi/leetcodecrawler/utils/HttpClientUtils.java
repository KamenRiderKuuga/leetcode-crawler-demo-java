package com.hanabi.leetcodecrawler.utils;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientUtils {

    private volatile static CloseableHttpClient httpClient = null;

    private HttpClientUtils (){}

    public static CloseableHttpClient getSingleton() {
        if (httpClient == null) {
            synchronized (HttpClientUtils.class) {
                if (httpClient == null) {
                    httpClient = HttpClientBuilder.create().build();
                }
            }
        }
        return httpClient;
    }
}
