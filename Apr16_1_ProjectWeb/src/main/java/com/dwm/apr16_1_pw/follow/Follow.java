package com.dwm.apr16_1_pw.follow;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Follow {
	private BigDecimal wf_no;
	private String wf_id;
	private String wf_follower;
	
	public Follow() {
		// TODO Auto-generated constructor stub
	}

	public Follow(BigDecimal wf_no, String wf_id, String wf_follower) {
		super();
		this.wf_no = wf_no;
		this.wf_id = wf_id;
		this.wf_follower = wf_follower;
	}

	public BigDecimal getWf_no() {
		return wf_no;
	}
	@XmlElement
	public void setWf_no(BigDecimal wf_no) {
		this.wf_no = wf_no;
	}

	public String getWf_id() {
		return wf_id;
	}
	@XmlElement
	public void setWf_id(String wf_id) {
		this.wf_id = wf_id;
	}

	public String getWf_follower() {
		return wf_follower;
	}
	@XmlElement
	public void setWf_follower(String wf_follower) {
		this.wf_follower = wf_follower;
	}
	
	
}
