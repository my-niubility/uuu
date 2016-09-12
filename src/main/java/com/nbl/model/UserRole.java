package com.nbl.model;

public class UserRole {
    private String loginaccountId;

    private String roleId;

    public String getLoginaccountId() {
        return loginaccountId;
    }

    public void setLoginaccountId(String loginaccountId) {
        this.loginaccountId = loginaccountId == null ? null : loginaccountId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}