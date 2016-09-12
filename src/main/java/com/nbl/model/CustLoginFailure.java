package com.nbl.model;

import java.util.Date;

public class CustLoginFailure {
    private String id;

    private String userId;

    private String ip;

    private Long failureCount;

    private String sessionId;

    private Date createdTime;

    private Date updateTime;

    private String lockTime;

    private Long currentFailCount;

    private String lockState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Long getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(Long failureCount) {
        this.failureCount = failureCount;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime == null ? null : lockTime.trim();
    }

    public Long getCurrentFailCount() {
        return currentFailCount;
    }

    public void setCurrentFailCount(Long currentFailCount) {
        this.currentFailCount = currentFailCount;
    }

    public String getLockState() {
        return lockState;
    }

    public void setLockState(String lockState) {
        this.lockState = lockState == null ? null : lockState.trim();
    }
}