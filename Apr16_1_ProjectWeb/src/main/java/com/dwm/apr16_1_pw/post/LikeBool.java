package com.dwm.apr16_1_pw.post;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class LikeBool {
	private BigDecimal wlb_no;
	private String wlb_id;
	private BigDecimal wlb_bool;
	private BigDecimal wlb_pno;
	
	public LikeBool() {
		// TODO Auto-generated constructor stub
	}

	public LikeBool(BigDecimal wlb_no, String wlb_id, BigDecimal wlb_bool, BigDecimal wlb_pno) {
		super();
		this.wlb_no = wlb_no;
		this.wlb_id = wlb_id;
		this.wlb_bool = wlb_bool;
		this.wlb_pno = wlb_pno;
	}

	public BigDecimal getWlb_no() {
		return wlb_no;
	}
	@XmlElement
	public void setWlb_no(BigDecimal wlb_no) {
		this.wlb_no = wlb_no;
	}

	public String getWlb_id() {
		return wlb_id;
	}
	@XmlElement
	public void setWlb_id(String wlb_id) {
		this.wlb_id = wlb_id;
	}

	public BigDecimal getWlb_bool() {
		return wlb_bool;
	}
	@XmlElement
	public void setWlb_bool(BigDecimal wlb_bool) {
		this.wlb_bool = wlb_bool;
	}

	public BigDecimal getWlb_pno() {
		return wlb_pno;
	}
	@XmlElement
	public void setWlb_pno(BigDecimal wlb_pno) {
		this.wlb_pno = wlb_pno;
	}
	
}
