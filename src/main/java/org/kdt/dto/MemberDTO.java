package org.kdt.dto;

public class MemberDTO {
	private String member_no;
	private String member_name;
	private String member_id;
	private String member_passwd;
	private String member_email;
	private int member_balance;
	private String member_role;
	
	public MemberDTO() {}

	public MemberDTO(String member_name, String member_id, String member_passwd, String member_email) {
		super();
		this.member_name = member_name;
		this.member_id = member_id;
		this.member_passwd = member_passwd;
		this.member_email = member_email;
	}

	public MemberDTO(String member_no, String member_name, String member_id, String member_passwd, String member_email,
			int member_balance, String member_role) {
		this.member_no = member_no;
		this.member_name = member_name;
		this.member_id = member_id;
		this.member_passwd = member_passwd;
		this.member_email = member_email;
		this.member_balance = member_balance;
		this.member_role = member_role;
	}

	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_passwd() {
		return member_passwd;
	}

	public void setMember_passwd(String member_passwd) {
		this.member_passwd = member_passwd;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public int getMember_balance() {
		return member_balance;
	}

	public void setMember_balance(int member_balance) {
		this.member_balance = member_balance;
	}

	public String getMember_role() {
		return member_role;
	}

	public void setMember_role(String member_role) {
		this.member_role = member_role;
	}

	@Override
	public String toString() {
		return "MemberDTO [member_no=" + member_no + ", member_name=" + member_name + ", member_id=" + member_id
				+ ", member_passwd=" + member_passwd + ", member_email=" + member_email + ", member_balance="
				+ member_balance + ", member_role=" + member_role + "]";
	}
	
	
}
