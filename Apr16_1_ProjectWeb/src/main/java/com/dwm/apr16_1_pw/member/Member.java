package com.dwm.apr16_1_pw.member;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Member {
	private String wm_id;
	private String wm_pw;
	private String wm_name;
	private String wm_add1;
	private String wm_add2;
	private String wm_add3;
	private Date wm_birth;
	private String wm_img;
	private String wm_open;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String wm_id, String wm_pw, String wm_name, String wm_add1, String wm_add2, String wm_add3,
			Date wm_birth, String wm_img, String wm_open) {
		super();
		this.wm_id = wm_id;
		this.wm_pw = wm_pw;
		this.wm_name = wm_name;
		this.wm_add1 = wm_add1;
		this.wm_add2 = wm_add2;
		this.wm_add3 = wm_add3;
		this.wm_birth = wm_birth;
		this.wm_img = wm_img;
		this.wm_open = wm_open;
	}

	public String getWm_id() {
		return wm_id;
	}
	@XmlElement
	public void setWm_id(String wm_id) {
		this.wm_id = wm_id;
	}

	public String getWm_pw() {
		return wm_pw;
	}
	@XmlElement
	public void setWm_pw(String wm_pw) {
		this.wm_pw = wm_pw;
	}

	public String getWm_name() {
		return wm_name;
	}
	@XmlElement
	public void setWm_name(String wm_name) {
		this.wm_name = wm_name;
	}

	public String getWm_add1() {
		return wm_add1;
	}
	@XmlElement
	public void setWm_add1(String wm_add1) {
		this.wm_add1 = wm_add1;
	}

	public String getWm_add2() {
		return wm_add2;
	}	
	@XmlElement
	public void setWm_add2(String wm_add2) {
		this.wm_add2 = wm_add2;
	}

	public String getWm_add3() {
		return wm_add3;
	}
	@XmlElement
	public void setWm_add3(String wm_add3) {
		this.wm_add3 = wm_add3;
	}

	public Date getWm_birth() {
		return wm_birth;
	}
	@XmlElement
	public void setWm_birth(Date wm_birth) {
		this.wm_birth = wm_birth;
	}

	public String getWm_img() {
		return wm_img;
	}
	@XmlElement
	public void setWm_img(String wm_img) {
		this.wm_img = wm_img;
	}

	public String getWm_open() {
		return wm_open;
	}
	@XmlElement
	public void setWm_open(String wm_open) {
		this.wm_open = wm_open;
	}

}
