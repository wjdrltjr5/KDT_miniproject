package org.kdt.dto;

public class MemberDTO {
    private String member_no;
    private String member_name;
    private String member_id;
    private String member_passwd;
    private String member_email;
    private String member_balance;
    private String member_role;

    public String getMember_no() {
        return member_no;
    }

    public String getMember_name() {
        return member_name;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getMember_passwd() {
        return member_passwd;
    }

    public String getMember_email() {
        return member_email;
    }

    public String getMember_balance() {
        return member_balance;
    }

    public String getMember_role() {
        return member_role;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public void setMember_passwd(String member_passwd) {
        this.member_passwd = member_passwd;
    }

}
