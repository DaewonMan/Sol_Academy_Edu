package com.dwm.apr16_1_pw.post;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Reple {
	private BigDecimal wr_no;
	private String wr_id;
	private BigDecimal wr_rno;
	private String wr_reple;
	private BigDecimal wr_likebool;
	
	public Reple() {
		// TODO Auto-generated constructor stub
	}
	
	public Reple(BigDecimal wr_no, String wr_id, BigDecimal wr_rno, String wr_reple, BigDecimal wr_likebool) {
		super();
		this.wr_no = wr_no;
		this.wr_id = wr_id;
		this.wr_rno = wr_rno;
		this.wr_reple = wr_reple;
		this.wr_likebool = wr_likebool;
	}



	public BigDecimal getWr_no() {
		return wr_no;
	}
	@XmlElement
	public void setWr_no(BigDecimal wr_no) {
		this.wr_no = wr_no;
	}

	public String getWr_id() {
		return wr_id;
	}
	@XmlElement
	public void setWr_id(String wr_id) {
		this.wr_id = wr_id;
	}

	public BigDecimal getWr_rno() {
		return wr_rno;
	}
	@XmlElement
	public void setWr_rno(BigDecimal wr_rno) {
		this.wr_rno = wr_rno;
	}

	public String getWr_reple() {
		return wr_reple;
	}
	@XmlElement
	public void setWr_reple(String wr_reple) {
		this.wr_reple = wr_reple;
	}

	public BigDecimal getWr_likebool() {
		return wr_likebool;
	}
	@XmlElement
	public void setWr_likebool(BigDecimal wr_likebool) {
		this.wr_likebool = wr_likebool;
	}
	
	
}
