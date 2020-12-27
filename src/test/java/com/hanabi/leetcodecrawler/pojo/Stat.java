package com.hanabi.leetcodecrawler.pojo;

public class Stat {

    private int question_id;
    private String question__title;
    private String question__title_slug;
    private boolean question__hide;
    private int total_acs;
    private int total_submitted;
    private int total_column_articles;
    private String frontend_question_id;
    private boolean is_new_question;
    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion__title(String question__title) {
        this.question__title = question__title;
    }
    public String getQuestion__title() {
        return question__title;
    }

    public void setQuestion__title_slug(String question__title_slug) {
        this.question__title_slug = question__title_slug;
    }
    public String getQuestion__title_slug() {
        return question__title_slug;
    }

    public void setQuestion__hide(boolean question__hide) {
        this.question__hide = question__hide;
    }
    public boolean getQuestion__hide() {
        return question__hide;
    }

    public void setTotal_acs(int total_acs) {
        this.total_acs = total_acs;
    }
    public int getTotal_acs() {
        return total_acs;
    }

    public void setTotal_submitted(int total_submitted) {
        this.total_submitted = total_submitted;
    }
    public int getTotal_submitted() {
        return total_submitted;
    }

    public void setTotal_column_articles(int total_column_articles) {
        this.total_column_articles = total_column_articles;
    }
    public int getTotal_column_articles() {
        return total_column_articles;
    }

    public void setFrontend_question_id(String frontend_question_id) {
        this.frontend_question_id = frontend_question_id;
    }
    public String getFrontend_question_id() {
        return frontend_question_id;
    }

    public void setIs_new_question(boolean is_new_question) {
        this.is_new_question = is_new_question;
    }
    public boolean getIs_new_question() {
        return is_new_question;
    }

}