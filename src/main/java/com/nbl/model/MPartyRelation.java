package com.nbl.model;

import java.util.Date;

public class MPartyRelation {
    private String id;

    private String type;

    private String fromPartyRoleId;

    private String toPartyRoleId;

    private Date createdTime;

    private Date periodStart;

    private Date periodEnd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFromPartyRoleId() {
        return fromPartyRoleId;
    }

    public void setFromPartyRoleId(String fromPartyRoleId) {
        this.fromPartyRoleId = fromPartyRoleId == null ? null : fromPartyRoleId.trim();
    }

    public String getToPartyRoleId() {
        return toPartyRoleId;
    }

    public void setToPartyRoleId(String toPartyRoleId) {
        this.toPartyRoleId = toPartyRoleId == null ? null : toPartyRoleId.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }
}