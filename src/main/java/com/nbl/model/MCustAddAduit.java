package com.nbl.model;

import java.util.Date;

public class MCustAddAduit {
    private String id;

    private String custId;

    private Short passed;

    private String auditUserId;

    private Short isModify;

    private String description;

    private Date createdTime;

    private byte[] newobject;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public Short getPassed() {
        return passed;
    }

    public void setPassed(Short passed) {
        this.passed = passed;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId == null ? null : auditUserId.trim();
    }

    public Short getIsModify() {
        return isModify;
    }

    public void setIsModify(Short isModify) {
        this.isModify = isModify;
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

    public byte[] getNewobject() {
        return newobject;
    }

    public void setNewobject(byte[] newobject) {
        this.newobject = newobject;
    }
}