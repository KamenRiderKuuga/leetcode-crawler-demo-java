package com.hanabi.leetcodecrawler.pojo;

import java.util.List;

/**
 * 用来解析题目列表的实体类，定义的字段名是为了和LeetCode统一，方便直接反序列化
 * 这里用的是直接把Json字符串反序列化成定义好的类的方法，具体使用时可以根据自己喜好
 */
public class QuestionsListResponse {

    private String user_name;
    private int num_solved;
    private int num_total;
    private int ac_easy;
    private int ac_medium;
    private int ac_hard;
    private List<Stat_status_pairs> stat_status_pairs;
    private int frequency_high;
    private int frequency_mid;
    private String category_slug;

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setNum_solved(int num_solved) {
        this.num_solved = num_solved;
    }

    public int getNum_solved() {
        return num_solved;
    }

    public void setNum_total(int num_total) {
        this.num_total = num_total;
    }

    public int getNum_total() {
        return num_total;
    }

    public void setAc_easy(int ac_easy) {
        this.ac_easy = ac_easy;
    }

    public int getAc_easy() {
        return ac_easy;
    }

    public void setAc_medium(int ac_medium) {
        this.ac_medium = ac_medium;
    }

    public int getAc_medium() {
        return ac_medium;
    }

    public void setAc_hard(int ac_hard) {
        this.ac_hard = ac_hard;
    }

    public int getAc_hard() {
        return ac_hard;
    }

    public void setStat_status_pairs(List<Stat_status_pairs> stat_status_pairs) {
        this.stat_status_pairs = stat_status_pairs;
    }

    public List<Stat_status_pairs> getStat_status_pairs() {
        return stat_status_pairs;
    }

    public void setFrequency_high(int frequency_high) {
        this.frequency_high = frequency_high;
    }

    public int getFrequency_high() {
        return frequency_high;
    }

    public void setFrequency_mid(int frequency_mid) {
        this.frequency_mid = frequency_mid;
    }

    public int getFrequency_mid() {
        return frequency_mid;
    }

    public void setCategory_slug(String category_slug) {
        this.category_slug = category_slug;
    }

    public String getCategory_slug() {
        return category_slug;
    }

}