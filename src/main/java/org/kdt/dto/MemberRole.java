package org.kdt.dto;

public enum MemberRole {
    USER("USER"),ADMIN("ADMIN");

    private String role;

    MemberRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
