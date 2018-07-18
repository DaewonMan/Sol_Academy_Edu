package com.dwm.apr16_1_pw.waiter;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Waiter {
	private BigDecimal wfw_no;
	private String wfw_id;
	private String wfw_follower;
	
	public Waiter() {
		// TODO Auto-generated constructor stub
	}

	public Waiter(BigDecimal wfw_no, String wfw_id, String wfw_follower) {
		super();
		this.wfw_no = wfw_no;
		this.wfw_id = wfw_id;
		this.wfw_follower = wfw_follower;
	}

	public BigDecimal getWfw_no() {
		return wfw_no;
	}
	@XmlElement
	public void setWfw_no(BigDecimal wfw_no) {
		this.wfw_no = wfw_no;
	}

	public String getWfw_id() {
		return wfw_id;
	}
	@XmlElement
	public void setWfw_id(String wfw_id) {
		this.wfw_id = wfw_id;
	}

	public String getWfw_follower() {
		return wfw_follower;
	}
	@XmlElement
	public void setWfw_follower(String wfw_follower) {
		this.wfw_follower = wfw_follower;
	}
	
	
}
