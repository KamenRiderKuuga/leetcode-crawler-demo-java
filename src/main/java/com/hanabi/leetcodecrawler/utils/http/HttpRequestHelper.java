package com.hanabi.leetcodecrawler.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRequestHelper {

    private CloseableHttpClient httpClient;
    private RequestConfig config;

    /**
     * @param httpClient httpClient实例
     * @param config     RequestConfig实例，用来设置Cookie规则，连接数等
     */
    public HttpRequestHelper(CloseableHttpClient httpClient, RequestConfig config) {
        this.httpClient = httpClient;
        this.config = config;
    }

    /**
     * 无参get请求，如果状态码为200，则返回body，否则返回null
     *
     * @param url 请求的url
     * @return UTF-8格式的请求结果字符串，如果状态码不为200，返回null
     */
    public HttpResult doGet(String url, Map<String, String> headers) throws IOException {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
        HttpResult httpResult = new HttpResult();

        // 装载配置信息
        httpGet.setConfig(config);

        // 设置header
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 带参get请求
     *
     * @param url     请求的url
     * @param map     携带的参数，若无则传null
     * @param headers 指定的请求头，若不需要特别指定则传null
     * @return UTF-8格式的请求结果字符串，如果状态码不为200，返回null
     */
    public HttpResult doGet(String url, Map<String, String> map, Map<String, String> headers) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, String> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString(), headers);
    }

    /**
     * 带参数的post请求
     *
     * @param url         请求的url
     * @param formContent form表单内容
     * @param headers     请求要带的header
     * @return HttpResult实体类，包含状态码和返回内容
     */
    public HttpResult doPost(String url, Map<String, Object> formContent, Map<String, String> headers) throws IOException {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        // 设置header
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        // 判断map是否为空，不为空则进行遍历，封装form表单对象
        if (formContent != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : formContent.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);

        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 不带参数post请求
     *
     * @param url 请求的url
     * @return HttpResult实体类，包含状态码和返回内容
     * @throws Exception
     */
    public HttpResult doPost(String url) throws IOException {
        return this.doPost(url, null, null);
    }

    /**
     * application/json类型的Post请求
     *
     * @param url         请求的url
     * @param jsonContent body内容，json字符串
     * @param headers     请求要带的header
     * @return HttpResult实体类，包含状态码和返回内容
     */
    public HttpResult doJsonPost(String url, String jsonContent, Map<String, String> headers) throws IOException {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        HttpEntity httpEntity = new StringEntity(jsonContent, "application/json; charset=utf-8", "UTF-8");
        httpPost.setEntity(httpEntity);

        // 设置header
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpResponse response = httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));

    }
}
