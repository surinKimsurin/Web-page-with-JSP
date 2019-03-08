package com.myspring.blog.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {

	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_gender;
	
	private String member_birth_y;
	private String member_birth_m;
	private String member_birth_d;
	private String member_birth_gn;

	
	private String member_tel;
	
	private String member_hp1;
	private String member_hp2;
	private String member_hp3;

	private String member_email1;
	private String member_email2;
	
	private String member_zipcode;
	private String member_address;
	private Date member_joinDate;

	
	public MemberVO(String member_id, String member_pw, String member_name, String member_gender, String member_birth_y,
			String member_birth_m, String member_birth_d, String member_birth_gn, String member_tel, String member_hp1,
			String member_hp2, String member_hp3, String member_email1, String member_email2, String member_zipcode,
			String member_address, Date member_joinDate) {
		super();
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.member_gender = member_gender;
		this.member_birth_y = member_birth_y;
		this.member_birth_m = member_birth_m;
		this.member_birth_d = member_birth_d;
		this.member_birth_gn = member_birth_gn;
		this.member_tel = member_tel;
		this.member_hp1 = member_hp1;
		this.member_hp2 = member_hp2;
		this.member_hp3 = member_hp3;
		this.member_email1 = member_email1;
		this.member_email2 = member_email2;
		this.member_zipcode = member_zipcode;
		this.member_address = member_address;
		this.member_joinDate = member_joinDate;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getMember_pw() {
		return member_pw;
	}


	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}


	public String getMember_name() {
		return member_name;
	}


	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}


	public String getMember_gender() {
		return member_gender;
	}


	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}


	public String getMember_birth_y() {
		return member_birth_y;
	}


	public void setMember_birth_y(String member_birth_y) {
		this.member_birth_y = member_birth_y;
	}


	public String getMember_birth_m() {
		return member_birth_m;
	}


	public void setMember_birth_m(String member_birth_m) {
		this.member_birth_m = member_birth_m;
	}


	public String getMember_birth_d() {
		return member_birth_d;
	}


	public void setMember_birth_d(String member_birth_d) {
		this.member_birth_d = member_birth_d;
	}


	public String getMember_birth_gn() {
		return member_birth_gn;
	}


	public void setMember_birth_gn(String member_birth_gn) {
		this.member_birth_gn = member_birth_gn;
	}


	public String getMember_tel() {
		return member_tel;
	}


	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}


	public String getMember_hp1() {
		return member_hp1;
	}


	public void setMember_hp1(String member_hp1) {
		this.member_hp1 = member_hp1;
	}


	public String getMember_hp2() {
		return member_hp2;
	}


	public void setMember_hp2(String member_hp2) {
		this.member_hp2 = member_hp2;
	}


	public String getMember_hp3() {
		return member_hp3;
	}


	public void setMember_hp3(String member_hp3) {
		this.member_hp3 = member_hp3;
	}


	public String getMember_email1() {
		return member_email1;
	}


	public void setMember_email1(String member_email1) {
		this.member_email1 = member_email1;
	}


	public String getMember_email2() {
		return member_email2;
	}


	public void setMember_email2(String member_email2) {
		this.member_email2 = member_email2;
	}


	public String getMember_zipcode() {
		return member_zipcode;
	}


	public void setMember_zipcode(String member_zipcode) {
		this.member_zipcode = member_zipcode;
	}


	public String getMember_address() {
		return member_address;
	}


	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}


	public Date getMember_joinDate() {
		return member_joinDate;
	}


	public void setMember_joinDate(Date member_joinDate) {
		this.member_joinDate = member_joinDate;
	}


	public MemberVO() {}
}
