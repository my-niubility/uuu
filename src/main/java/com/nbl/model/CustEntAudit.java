package com.nbl.model;

import java.util.Date;

public class CustEntAudit {
    private String id;

    private String custId;

    private String enterpriseChangeId;

    private String type;

    private Short passed;

    private Date createdTime;

    private String auditCustId;

    private Short isModify;

    private String description;

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

    public String getEnterpriseChangeId() {
        return enterpriseChangeId;
    }

    public void setEnterpriseChangeId(String enterpriseChangeId) {
        this.enterpriseChangeId = enterpriseChangeId == null ? null : enterpriseChangeId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Short getPassed() {
        return passed;
    }

    public void setPassed(Short passed) {
        this.passed = passed;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getAuditCustId() {
        return auditCustId;
    }

    public void setAuditCustId(String auditCustId) {
        this.auditCustId = auditCustId == null ? null : auditCustId.trim();
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
}