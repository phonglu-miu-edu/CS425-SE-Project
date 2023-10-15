package com.swe.lms.admin.api.constant;

public enum BookStatus {
    AVAILABLE("Available"),
    BORROWED("Borrowed"),
    DELETED("Deleted");
    private String status;
    BookStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return this.status;
    }
}
