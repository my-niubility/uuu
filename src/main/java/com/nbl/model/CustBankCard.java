package com.nbl.model;

import java.util.Date;

public class CustBankCard {
    private String id;

    private String custId;

    private String cardName;

    private String cardNo;

    private String cardType;

    private String bankType;

    private Date createDate;

    private Date updateTime;

    private String isSend;

    private String status;

    private String isProtocol;

    private String isCert;

    private String authNo;

    private String branchName;

    private String auditStatus;

    private String auditBindingStatus;

    private String firstGeo;

    private String secondGeo;

    private String createType;
    
    private String isDefault;

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

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType == null ? null : bankType.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend == null ? null : isSend.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsProtocol() {
        return isProtocol;
    }

    public void setIsProtocol(String isProtocol) {
        this.isProtocol = isProtocol == null ? null : isProtocol.trim();
    }

    public String getIsCert() {
        return isCert;
    }

    public void setIsCert(String isCert) {
        this.isCert = isCert == null ? null : isCert.trim();
    }

    public String getAuthNo() {
        return authNo;
    }

    public void setAuthNo(String authNo) {
        this.authNo = authNo == null ? null : authNo.trim();
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getAuditBindingStatus() {
        return auditBindingStatus;
    }

    public void setAuditBindingStatus(String auditBindingStatus) {
        this.auditBindingStatus = auditBindingStatus == null ? null : auditBindingStatus.trim();
    }

    public String getFirstGeo() {
        return firstGeo;
    }

    public void setFirstGeo(String firstGeo) {
        this.firstGeo = firstGeo == null ? null : firstGeo.trim();
    }

    public String getSecondGeo() {
        return secondGeo;
    }

    public void setSecondGeo(String secondGeo) {
        this.secondGeo = secondGeo == null ? null : secondGeo.trim();
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType == null ? null : createType.trim();
    }

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault == null ? null : isDefault.trim();
	}
    
    
}