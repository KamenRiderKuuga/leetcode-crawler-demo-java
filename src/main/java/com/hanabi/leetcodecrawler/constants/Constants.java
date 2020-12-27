package com.hanabi.leetcodecrawler.constants;

public class Constants {

    // LeetCode的主网址
    public static final String LEETCODE_LOGIN_NAME = "example@qq.com";

    // 登录时请求的url
    public static final String LEETCODE_PASSWORD = "example";

    // LeetCode的主网址
    public static final String LEETCODE_URL = "https://leetcode-cn.com/";

    // 登录时请求的url
    public static final String SIGN_IN_URL = "https://leetcode-cn.com/accounts/login/";

    // 请求时默认携带的User-Agent
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36 Edg/87.0.664.66";

    // 获取LeetCode题目内容时的查询字符串
    public static final String LEETCODE_PROBLEM_DETAIL = "{\"operationName\":\"questionData\",\"variables\":{\"titleSlug\":\"%s\"}," +
            "\"query\":\"query questionData($titleSlug: String!) {question(titleSlug: $titleSlug) " +
            "{title    content    translatedTitle    translatedContent    topicTags {name      slug      translatedName}}}\"}";

    // 获取LeetCode题目内容时的URL
    public static final String LEETCODE_PROBLEM_CONTENT_URL = "https://leetcode-cn.com/problems/%s/";

    //获取LeetCode所有题目列表的URL
    public static final String LEETCODE_PROBLEMS_LIST_URL = "https://leetcode-cn.com/api/problems/all/";

    // LeetCode的graphql接口
    public static final String LEETCODE_GRAPHQL = "https://leetcode-cn.com/graphql";

    // LeetCode提交题解的URL
    public static final String LEETCODE_SUBMIT_URL = "https://leetcode-cn.com/problems/%s/submit/";

    // LeetCode查询判题结果的URL
    public static final String LEETCODE_QUERY_SUBMISSION = "https://leetcode-cn.com/submissions/detail/%s/check/";

    // 触发LeetCode访问频率限制时出现的状态码
    public static final int STATUS_TOO_MANY_REQUESTS = 429;

    // 查询LeetCode时若判题还未结束，其返回的state字段的内容
    public static final String LEETCODE_STATE_STARTED = "STARTED";

    // 查询LeetCode时若查询的提交编号无效或该题已经无法查询，其返回的state字段的内容
    public static final String LEETCODE_STATE_PENDING = "PENDING";
}
