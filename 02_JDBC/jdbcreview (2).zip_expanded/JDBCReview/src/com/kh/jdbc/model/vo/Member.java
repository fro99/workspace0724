package com.kh.jdbc.model.vo;

public class Member {
	private Long memberid;
	private String membername;
	private String phone;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getMemberid() {
		return memberid;
	}
	public void setMemberid(Long memberid) {
		this.memberid = memberid;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return " [memberid=" + memberid + ", membername=" + membername + ", phone=" + phone + "]";
	}
}
