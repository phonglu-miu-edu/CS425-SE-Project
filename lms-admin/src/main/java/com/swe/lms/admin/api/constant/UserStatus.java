package com.swe.lms.admin.api.constant;

public enum UserStatus {
    ACTIVE("Active"),
    LOCKED("Locked"),
    SUSPENDED("Suspended");
    private String status;
    UserStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return this.status;
    }
}
