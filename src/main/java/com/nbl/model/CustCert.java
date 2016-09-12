package com.nbl.model;

import java.util.Date;

public class CustCert {
    private String id;

    private String credentialsType;

    private String custId;

    private String certFileType;

    private Date certUpdateTime;

    private byte[] certFile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType == null ? null : credentialsType.trim();
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public String getCertFileType() {
        return certFileType;
    }

    public void setCertFileType(String certFileType) {
        this.certFileType = certFileType == null ? null : certFileType.trim();
    }

    public Date getCertUpdateTime() {
        return certUpdateTime;
    }

    public void setCertUpdateTime(Date certUpdateTime) {
        this.certUpdateTime = certUpdateTime;
    }

    public byte[] getCertFile() {
        return certFile;
    }

    public void setCertFile(byte[] certFile) {
        this.certFile = certFile;
    }
}