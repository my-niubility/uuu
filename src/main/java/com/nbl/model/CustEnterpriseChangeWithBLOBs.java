package com.nbl.model;

public class CustEnterpriseChangeWithBLOBs extends CustEnterpriseChange {
    private byte[] originalObject;

    private byte[] newObject;

    public byte[] getOriginalObject() {
        return originalObject;
    }

    public void setOriginalObject(byte[] originalObject) {
        this.originalObject = originalObject;
    }

    public byte[] getNewObject() {
        return newObject;
    }

    public void setNewObject(byte[] newObject) {
        this.newObject = newObject;
    }
}