package com.nbl.model;

import java.util.Date;

public class MLoginAccount {
    private String accountId;

    private String loginName;

    private String password;

    private String mobile;

    private String email;

    private String partyId;

    private String realName;

    private String salt;

    private String roleId;

    private String useCa;

    private String lockStatus;

    private String enableStatus;

    private Date lastUpdatePasswordTime;

    private Date lastLoginLockTime;

    private String description;

    private Date createDate;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId == null ? null : partyId.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getUseCa() {
        return useCa;
    }

    public void setUseCa(String useCa) {
        this.useCa = useCa == null ? null : useCa.trim();
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus == null ? null : lockStatus.trim();
    }

    public String getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus == null ? null : enableStatus.trim();
    }

    public Date getLastUpdatePasswordTime() {
        return lastUpdatePasswordTime;
    }

    public void setLastUpdatePasswordTime(Date lastUpdatePasswordTime) {
        this.lastUpdatePasswordTime = lastUpdatePasswordTime;
    }

    public Date getLastLoginLockTime() {
        return lastLoginLockTime;
    }

    public void setLastLoginLockTime(Date lastLoginLockTime) {
        this.lastLoginLockTime = lastLoginLockTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}