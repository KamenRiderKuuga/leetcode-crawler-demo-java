package com.hanabi;

import java.io.*;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Main {
    public final static String questionData = "{\"operationName\":\"questionData\",\"variables\":{\"titleSlug\":\"%s\"},\"query\":\"query questionData($titleSlug: String!) {question(titleSlug: $titleSlug) {title    content    translatedTitle    translatedContent    topicTags {name      slug      translatedName}}}\"}";

    public static void main(String[] args) {
        try {
            String leetCodeUrl = "https://leetcode-cn.com";
            String signInUrl = "https://leetcode-cn.com/accounts/login/";
            String loginName = "524472212@qq.com";
            String password = "199654088";

            HttpPost loginRequest = new HttpPost(signInUrl);

            // 初始化header
            loginRequest.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
            loginRequest.setHeader("Connection", "keep-alive");
            loginRequest.setHeader("Referer", signInUrl);
            loginRequest.setHeader("origin", leetCodeUrl);

            HttpEntity loginEntity = new StringEntity("login=" + loginName + "&password=" + password,
                    "application/x-www-form-urlencoded", "UTF-8");

            loginRequest.setEntity(loginEntity);

            // 定义一个CookieStore用来保存Cookie
            CookieStore httpCookieStore = new BasicCookieStore();
            HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);

            // 使用一个httpClient 实现会话维持的效果
            CloseableHttpClient httpClient = builder.build();
            HttpGet httpGet = new HttpGet(signInUrl);
            httpClient.execute(httpGet);
            CloseableHttpResponse response = httpClient.execute(loginRequest);
            httpCookieStore.getCookies();
            Header[] headers = response.getHeaders("Set-Cookie");

            String postBody = String.format(questionData, "two-sum");
            HttpPost request = new HttpPost(leetCodeUrl + "/graphql");
            request.setHeader("Content-Type","application/json");
            request.setHeader("Referer", String.format("https://leetcode-cn.com/problems/%s/","two-sum" ));
            request.setHeader("origin", leetCodeUrl);

            HttpEntity httpEntity = new StringEntity(postBody, "application/json; charset=utf-8", "UTF-8");
            request.setEntity(httpEntity);

            response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String html = decodeUnicode(EntityUtils.toString(entity, "utf-8"));
                System.out.println(html);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Unicode转中文
    public static String decodeUnicode(final String unicode) {
        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 0; i < hex.length; i++) {

            try {
                // 汉字范围 \u4e00-\u9fa5 (中文)
                if(hex[i].length()>=4){//取前四个，判断是否是汉字
                    String chinese = hex[i].substring(0, 4);
                    try {
                        int chr = Integer.parseInt(chinese, 16);
                        boolean isChinese = isChinese((char) chr);
                        //转化成功，判断是否在  汉字范围内
                        if (isChinese){//在汉字范围内
                            // 追加成string
                            string.append((char) chr);
                            //并且追加  后面的字符
                            String behindString = hex[i].substring(4);
                            string.append(behindString);
                        }else {
                            string.append(hex[i]);
                        }
                    } catch (NumberFormatException e1) {
                        string.append(hex[i]);
                    }

                }else{
                    string.append(hex[i]);
                }
            } catch (NumberFormatException e) {
                string.append(hex[i]);
            }
        }

        return string.toString();
    }

    /**
     * 判断是否为中文字符
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
}
