package com.hanabi.leetcodecrawler.pojo;

public class Stat_status_pairs {

    private Stat stat;
    private String status;
    private Difficulty difficulty;
    private boolean paid_only;
    private boolean is_favor;
    private int frequency;
    private int progress;
    public void setStat(Stat stat) {
        this.stat = stat;
    }
    public Stat getStat() {
        return stat;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setPaid_only(boolean paid_only) {
        this.paid_only = paid_only;
    }
    public boolean getPaid_only() {
        return paid_only;
    }

    public void setIs_favor(boolean is_favor) {
        this.is_favor = is_favor;
    }
    public boolean getIs_favor() {
        return is_favor;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public int getFrequency() {
        return frequency;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
    public int getProgress() {
        return progress;
    }

}