package com.swe.lms.admin.api.constant;

public enum RoleEnum {
    ADMIN("Admin"),
    LIBRARIAN("Librarian"),
    USER("User");
    private String value;
    RoleEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}
