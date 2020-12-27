package com.hanabi.leetcodecrawler.pojo;

public class AnswerParam {

    /**
     * 提交的题目的id
     */
    private long id;

    /**
     * 提交的题解内容
     */
    private String answer;

    /**
     * 提交题解的语言类型
     */
    private int language;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }
}
