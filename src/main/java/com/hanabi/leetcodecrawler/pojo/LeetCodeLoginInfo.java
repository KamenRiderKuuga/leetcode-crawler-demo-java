package com.hanabi.leetcodecrawler.pojo;

/**
 * LeetCode的登陆信息，暂时包括sessionId和csrfToken，后续有需要可以扩展
 */
public class LeetCodeLoginInfo {
    private String sessionId;
    private String csrfToken;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }
}
