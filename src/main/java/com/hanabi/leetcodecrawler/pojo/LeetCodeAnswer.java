package com.hanabi.leetcodecrawler.pojo;

public class LeetCodeAnswer {
    private String judge_type;
    private String lang;
    private int question_id;
    private boolean test_mode;
    private String typed_code;

    public String getJudge_type() {
        return judge_type;
    }

    public void setJudge_type(String judge_type) {
        this.judge_type = judge_type;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public boolean getTest_mode() {
        return test_mode;
    }

    public void setTest_mode(boolean test_mode) {
        this.test_mode = test_mode;
    }

    public String getTyped_code() {
        return typed_code;
    }

    public void setTyped_code(String typed_code) {
        this.typed_code = typed_code;
    }
}
