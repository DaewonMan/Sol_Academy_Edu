package com.dwm.apr16_1_pw.post;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Post {
	private BigDecimal wp_no;
	private String wp_id;
	private String wp_title;
	private String wp_hash;
	private Date wp_date;
	private String wp_img;
	private BigDecimal wp_like;
	
	public Post() {
		// TODO Auto-generated constructor stub
	}

	public Post(BigDecimal wp_no, String wp_id, String wp_title, String wp_hash, Date wp_date, String wp_img,
			BigDecimal wp_like) {
		super();
		this.wp_no = wp_no;
		this.wp_id = wp_id;
		this.wp_title = wp_title;
		this.wp_hash = wp_hash;
		this.wp_date = wp_date;
		this.wp_img = wp_img;
		this.wp_like = wp_like;
	}

	public BigDecimal getWp_like() {
		return wp_like;
	}
	@XmlElement
	public void setWp_like(BigDecimal wp_like) {
		this.wp_like = wp_like;
	}

	public BigDecimal getWp_no() {
		return wp_no;
	}
	@XmlElement
	public void setWp_no(BigDecimal wp_no) {
		this.wp_no = wp_no;
	}

	public String getWp_id() {
		return wp_id;
	}
	@XmlElement
	public void setWp_id(String wp_id) {
		this.wp_id = wp_id;
	}

	public String getWp_title() {
		return wp_title;
	}
	@XmlElement
	public void setWp_title(String wp_title) {
		this.wp_title = wp_title;
	}

	public String getWp_hash() {
		return wp_hash;
	}
	@XmlElement
	public void setWp_hash(String wp_hash) {
		this.wp_hash = wp_hash;
	}

	public Date getWp_date() {
		return wp_date;
	}
	@XmlElement
	public void setWp_date(Date wp_date) {
		this.wp_date = wp_date;
	}

	public String getWp_img() {
		return wp_img;
	}
	@XmlElement
	public void setWp_img(String wp_img) {
		this.wp_img = wp_img;
	}
	
	
	
}
