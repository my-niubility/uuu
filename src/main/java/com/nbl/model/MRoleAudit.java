package com.nbl.model;

import java.util.Date;

public class MRoleAudit {
    private String id;

    private String roleChangeId;

    private String userId;

    private String result;

    private String description;

    private Date createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleChangeId() {
        return roleChangeId;
    }

    public void setRoleChangeId(String roleChangeId) {
        this.roleChangeId = roleChangeId == null ? null : roleChangeId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}