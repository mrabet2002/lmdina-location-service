package com.mdina.location.enumerations;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER"), ROLE_COMPANY_OWNER("ROLE_COMPANY_OWNER");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
