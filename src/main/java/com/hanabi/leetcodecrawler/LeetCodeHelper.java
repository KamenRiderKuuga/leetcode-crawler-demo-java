package com.hanabi.leetcodecrawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hanabi.leetcodecrawler.constants.Constants;
import com.hanabi.leetcodecrawler.constants.LanguageEnums;
import com.hanabi.leetcodecrawler.pojo.LeetCodeAnswer;
import com.hanabi.leetcodecrawler.pojo.LeetCodeLoginInfo;
import com.hanabi.leetcodecrawler.pojo.AnswerParam;
import com.hanabi.leetcodecrawler.utils.StringUtil;
import com.hanabi.leetcodecrawler.utils.http.HttpRequestHelper;
import com.hanabi.leetcodecrawler.utils.http.HttpResult;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class LeetCodeHelper {
    private final String leetCodeLoginName;

    private final String leetCodePassword;

    private final HttpRequestHelper httpRequestHelper;

    CookieStore cookieStore;

    /**
     * @param httpClient  httpClient实例
     * @param config      RequestConfig实例，用来设置Cookie规则，连接数等
     * @param cookieStore httpClient实例对应的cookieStore实例
     */
    public LeetCodeHelper(CloseableHttpClient httpClient, RequestConfig config, CookieStore cookieStore) {
        this.leetCodeLoginName = Constants.LEETCODE_LOGIN_NAME;
        this.leetCodePassword = Constants.LEETCODE_PASSWORD;
        this.httpRequestHelper = new HttpRequestHelper(httpClient, config);
        this.cookieStore = cookieStore;
    }

    /**
     * 使用配置好的账号密码登录到LeetCode
     *
     * @return 登录结果
     */
    public boolean loginToLeetCode() throws IOException {
        Map<String, Object> formContent = new HashMap();
        formContent.put("login", leetCodeLoginName);
        formContent.put("password", leetCodePassword);

        Map<String, String> headers = getDefaultHeader();
        headers.put("Referer", Constants.SIGN_IN_URL);

        httpRequestHelper.doPost(Constants.SIGN_IN_URL, formContent, headers);

        // 请求过后，若当前Cookies数量大于等于3个，则登陆成功
        return cookieStore.getCookies().size() >= 3;
    }

    /**
     * 根据题目Slug获取题目内容
     *
     * @param questionSlug 题目的Slug，对LeetCode来说就是每道题的url的最后一部分
     * @return LeetCode返回的题目内容
     */
    public String getQuestionDetailBySlug(String questionSlug) throws IOException {
        Map<String, String> headers = getDefaultHeader();
        headers.put("Content-Type", "application/json");
        headers.put("Referer", String.format(Constants.LEETCODE_PROBLEM_CONTENT_URL, questionSlug));

        String postBody = String.format(Constants.LEETCODE_PROBLEM_DETAIL, questionSlug);

        HttpResult result = httpRequestHelper.doJsonPost(Constants.LEETCODE_GRAPHQL, postBody, headers);

        return StringUtil.decodeUnicode(result.getBody());
    }

    /**
     * 返回LeetCode题目Json字符串
     *
     * @return
     * @throws IOException
     */
    public String getQuestionsList() throws IOException {
        HttpResult result = httpRequestHelper.doGet(Constants.LEETCODE_PROBLEMS_LIST_URL, null);
        if (result.getCode() == HttpStatus.SC_OK) {
            return result.getBody();
        }
        return "";
    }

    /**
     * 提交题解到LeetCode，并且返回流水号
     *
     * @param answer       题解实体
     * @param questionSlug LeetCode题目Slug
     * @param retry        是否允许重试(第一次提交可能会因为cookie无效等原因，需要刷新cookie，此时如果获取题目失败允许重试一次)
     * @param questionId   LeetCode平台的题目id
     * @return LeetCode的题目提交流水号
     */
    public String submitAnswerToLeetCode(AnswerParam answer, String questionSlug, int questionId, boolean retry) throws IOException, InterruptedException {
        // 提交题目时使用的url
        String url = String.format(Constants.LEETCODE_SUBMIT_URL, questionSlug);

        // 设置请求参数，judge_type,test_mode参数使用的是默认值
        LeetCodeAnswer leetCodeAnswer = new LeetCodeAnswer();
        leetCodeAnswer.setJudge_type("large");
        leetCodeAnswer.setLang(LanguageEnums.getName(answer.getLanguage()));
        leetCodeAnswer.setQuestion_id(questionId);
        leetCodeAnswer.setTest_mode(false);
        leetCodeAnswer.setTyped_code(answer.getAnswer());
        String postContent = JSON.toJSONString(leetCodeAnswer);

        // 设置请求头
        Map<String, String> headers = getHeaderWithLoginInfo(String.format(Constants.LEETCODE_PROBLEM_CONTENT_URL, questionSlug));

        HttpResult result = null;

        result = httpRequestHelper.doJsonPost(url, postContent, headers);

        switch (result.getCode()) {
            case HttpStatus.SC_OK:
                Map submitResult = JSON.parseObject(result.getBody(), Map.class);
                for (Object item : submitResult.entrySet()) {
                    Map.Entry<String, Object> entry = (Map.Entry) item;
                    // 返回结果中获取到的提交流水号
                    if (entry.getKey().equals("submission_id")) {
                        return entry.getValue().toString();
                    }
                }
                break;
            case Constants.STATUS_TOO_MANY_REQUESTS:
                // 触发了LeetCode的请求频率限制，Sleep一秒，重新请求
                Thread.sleep(1000);
                retry = true;
                break;
        }

        if (retry) {
            return submitAnswerToLeetCode(answer, questionSlug, questionId, false);
        }

        return null;
    }


    /**
     * 查询判题结果
     *
     * @param submissionId 题目提交id
     * @param questionSlug LeetCode的题目Slug，如two-sum
     * @return
     */
    public HttpResult queryJudgementResult(String submissionId, String questionSlug) throws IOException, URISyntaxException {
        String url = String.format(Constants.LEETCODE_QUERY_SUBMISSION, submissionId);
        String refererUrl = String.format(Constants.LEETCODE_PROBLEM_CONTENT_URL, questionSlug);

        Map<String, String> headers = getHeaderWithLoginInfo(refererUrl);

        HttpResult httpResult = null;

        httpResult = httpRequestHelper.doGet(url, null, headers);

        return httpResult;
    }

    /**
     * 获取每次请求LeetCode都需要的默认Header
     *
     * @return 含有必填项的headers
     */
    private Map<String, String> getDefaultHeader() {
        Map<String, String> headers = new HashMap();
        headers.put("User-Agent", Constants.USER_AGENT);
        headers.put("origin", Constants.LEETCODE_URL);
        return headers;
    }

    /**
     * 为登录状态才能使用的请求构建完整Header
     *
     * @param refererUrl 需要加在header中的Referer，用于标记来源页面
     * @return 含有完整登录信息的headers
     */
    private Map<String, String> getHeaderWithLoginInfo(String refererUrl) throws IOException {
        // 如果Cookie的数量还没达到三个，说明从来没有登录过，这里需要先进行登录
        if (cookieStore.getCookies().size() < 3) {
            loginToLeetCode();
        }

        LeetCodeLoginInfo loginInfo = getLoginInfo();

        // 设置请求头
        Map<String, String> headers = getDefaultHeader();

        headers.put("Cookie", "LEETCODE_SESSION=" + loginInfo.getSessionId() + ";csrftoken=" + loginInfo.getCsrfToken() + ";");
        headers.put("X-CSRFToken", loginInfo.getCsrfToken());
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("Referer", refererUrl);

        return headers;
    }

    /**
     * 获取登录信息
     *
     * @return 登录信息实体
     */
    private LeetCodeLoginInfo getLoginInfo() {
        LeetCodeLoginInfo loginInfo = new LeetCodeLoginInfo();
        String sessionId = "";
        String csrfToken = "";

        // 添加必要的Cookie
        for (Cookie cookie : cookieStore.getCookies()) {
            if (cookie.getName().equals("LEETCODE_SESSION")) {
                sessionId = cookie.getValue();
            }

            if (cookie.getName().equals("csrftoken")) {
                csrfToken = cookie.getValue();
            }
        }

        loginInfo.setSessionId(sessionId);
        loginInfo.setCsrfToken(csrfToken);

        return loginInfo;
    }
}
