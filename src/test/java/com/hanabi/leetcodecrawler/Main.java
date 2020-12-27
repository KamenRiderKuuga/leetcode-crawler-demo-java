package com.hanabi.leetcodecrawler;

import com.alibaba.fastjson.JSONObject;
import com.hanabi.leetcodecrawler.constants.Constants;
import com.hanabi.leetcodecrawler.constants.LanguageEnums;
import com.hanabi.leetcodecrawler.pojo.AnswerParam;
import com.hanabi.leetcodecrawler.pojo.LeetCodeJudgementResult;
import com.hanabi.leetcodecrawler.pojo.QuestionsListResponse;
import com.hanabi.leetcodecrawler.pojo.Stat_status_pairs;
import com.hanabi.leetcodecrawler.utils.http.HttpResult;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        CookieStore httpCookieStore = new BasicCookieStore();
        // 使用HttpClientBuilder提供的静态方法create()来获取HttpClientBuilder对象
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
        CloseableHttpClient httpClient = httpClientBuilder.build();

        // 设置RequestConfig，还可以使用它来控制超时时间等，这里只用来设置CookieSpec
        RequestConfig.Builder builder = RequestConfig.custom();
        RequestConfig requestConfig = builder.setCookieSpec(CookieSpecs.STANDARD).build();

        // 用准备好的参数实例化LeetCodeHelper
        LeetCodeHelper leetCodeHelper = new LeetCodeHelper(httpClient, requestConfig, httpCookieStore);

        // 获取所有题目列表
        String questionsListJsonString = leetCodeHelper.getQuestionsList();
        QuestionsListResponse questionsListResponse = JSONObject.parseObject(questionsListJsonString, QuestionsListResponse.class);
        System.out.println("题目数量合计:" + questionsListResponse.getNum_total());

        // 找到我们熟悉的two-sum这道题，LeetCode用一个叫做slug的属性来代表我们在url里看到的值
        String questionSlug = "two-sum";
        Optional<Stat_status_pairs> questionTwoSum = questionsListResponse.getStat_status_pairs()
                .stream().filter(item -> item.getStat().getQuestion__title_slug().equals(questionSlug)).findFirst();

        int questionId = 0;

        if (questionTwoSum.isPresent()) {
            // 这里记录two-sum的id，用于之后提交使用
            questionId = questionTwoSum.get().getStat().getQuestion_id();
            System.out.println("题目id为:" + questionId);
        }

        // 获取题目详情
        String questionContentJsonString = leetCodeHelper.getQuestionDetailBySlug(questionSlug);
        System.out.println("题目详情:" + questionContentJsonString);

        // 构建题解
        AnswerParam answerParam = new AnswerParam();
        answerParam.setAnswer("print(\"hello, world!\")");
        answerParam.setLanguage(LanguageEnums.PYTHON3.getCode());

        // 提交题解并返回提交流水号
        String submissionId = leetCodeHelper.submitAnswerToLeetCode(answerParam, questionSlug, questionId, true);
        System.out.println("提交流水号为:" + submissionId);

        // 使用流水获取判题结果
        String judgementResultJsonString = "";
        LeetCodeJudgementResult judgementResult = new LeetCodeJudgementResult();
        judgementResult.setState(Constants.LEETCODE_STATE_STARTED);

        while (judgementResult.getState().equals(Constants.LEETCODE_STATE_STARTED)) {
            judgementResultJsonString = leetCodeHelper.queryJudgementResult(submissionId, questionSlug).getBody();
            judgementResult = JSONObject.parseObject(judgementResultJsonString, LeetCodeJudgementResult.class);
            System.out.println("正在判题中，请稍等");
        }

        System.out.println("判题结果:" + judgementResultJsonString);
    }
}
