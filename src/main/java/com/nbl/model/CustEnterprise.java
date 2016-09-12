package com.nbl.model;

import java.util.Date;

public class CustEnterprise {
    private String id;

    private String name;

    private String credentialsType;

    private String phone;

    private String fax;

    private String address;

    private String summary;

    private String companyImg;

    private String organiztionImg;

    private String taxImg;

    private String bankAccImg;

    private String status;

    private String certStatus;

    private String prStatus;

    private String legalpersonId;

    private String legalpersonBackId;

    private String legalpersonMobile;

    private String companyPoaId;

    private String agentId;

    private String agentBackId;

    private String agentName;

    private String agentMobile;

    private Date updateDate;

    private Date createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType == null ? null : credentialsType.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getCompanyImg() {
        return companyImg;
    }

    public void setCompanyImg(String companyImg) {
        this.companyImg = companyImg == null ? null : companyImg.trim();
    }

    public String getOrganiztionImg() {
        return organiztionImg;
    }

    public void setOrganiztionImg(String organiztionImg) {
        this.organiztionImg = organiztionImg == null ? null : organiztionImg.trim();
    }

    public String getTaxImg() {
        return taxImg;
    }

    public void setTaxImg(String taxImg) {
        this.taxImg = taxImg == null ? null : taxImg.trim();
    }

    public String getBankAccImg() {
        return bankAccImg;
    }

    public void setBankAccImg(String bankAccImg) {
        this.bankAccImg = bankAccImg == null ? null : bankAccImg.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(String certStatus) {
        this.certStatus = certStatus == null ? null : certStatus.trim();
    }

    public String getPrStatus() {
        return prStatus;
    }

    public void setPrStatus(String prStatus) {
        this.prStatus = prStatus == null ? null : prStatus.trim();
    }

    public String getLegalpersonId() {
        return legalpersonId;
    }

    public void setLegalpersonId(String legalpersonId) {
        this.legalpersonId = legalpersonId == null ? null : legalpersonId.trim();
    }

    public String getLegalpersonBackId() {
        return legalpersonBackId;
    }

    public void setLegalpersonBackId(String legalpersonBackId) {
        this.legalpersonBackId = legalpersonBackId == null ? null : legalpersonBackId.trim();
    }

    public String getLegalpersonMobile() {
        return legalpersonMobile;
    }

    public void setLegalpersonMobile(String legalpersonMobile) {
        this.legalpersonMobile = legalpersonMobile == null ? null : legalpersonMobile.trim();
    }

    public String getCompanyPoaId() {
        return companyPoaId;
    }

    public void setCompanyPoaId(String companyPoaId) {
        this.companyPoaId = companyPoaId == null ? null : companyPoaId.trim();
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
    }

    public String getAgentBackId() {
        return agentBackId;
    }

    public void setAgentBackId(String agentBackId) {
        this.agentBackId = agentBackId == null ? null : agentBackId.trim();
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public String getAgentMobile() {
        return agentMobile;
    }

    public void setAgentMobile(String agentMobile) {
        this.agentMobile = agentMobile == null ? null : agentMobile.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}