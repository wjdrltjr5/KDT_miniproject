package org.kdt.dto;

public enum MembersProductStatus {
    HOLD("대기"), SUCCESS("허가"), FAILURE("거절");

    private String text;
    MembersProductStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
