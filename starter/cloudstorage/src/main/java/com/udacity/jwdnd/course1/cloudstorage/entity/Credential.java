package com.udacity.jwdnd.course1.cloudstorage.entity;

public class Credential {
    private Integer credentialId;
    private String credentialUrl;
    private String username;
    private String password;
    private String key;
    private Integer userId;

    public Credential(Integer credentialId, String credentialUrl, String username, String password, String key, Integer userId) {
        this.credentialId = credentialId;
        this.credentialUrl = credentialUrl;
        this.username = username;
        this.password = password;
        this.key = key;
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getCredentialUrl() {
        return credentialUrl;
    }

    public void setCredentialUrl(String credentialUrl) {
        this.credentialUrl = credentialUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
