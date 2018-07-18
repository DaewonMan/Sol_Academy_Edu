package com.dwm.apr16_1_pw.member;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Members {
	private List<Member> member;
	
	public Members() {
		// TODO Auto-generated constructor stub
	}

	public Members(List<Member> member) {
		super();
		this.member = member;
	}

	public List<Member> getMember() {
		return member;
	}
	@XmlElement
	public void setMember(List<Member> member) {
		this.member = member;
	}
	
	
}
