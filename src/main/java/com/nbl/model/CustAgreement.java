package com.nbl.model;

public class CustAgreement {
    private String agrtId;

    private String custId;

    private String agrtType;

    private byte[] agrtContent;

    public String getAgrtId() {
        return agrtId;
    }

    public void setAgrtId(String agrtId) {
        this.agrtId = agrtId == null ? null : agrtId.trim();
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public String getAgrtType() {
        return agrtType;
    }

    public void setAgrtType(String agrtType) {
        this.agrtType = agrtType == null ? null : agrtType.trim();
    }

    public byte[] getAgrtContent() {
        return agrtContent;
    }

    public void setAgrtContent(byte[] agrtContent) {
        this.agrtContent = agrtContent;
    }
}