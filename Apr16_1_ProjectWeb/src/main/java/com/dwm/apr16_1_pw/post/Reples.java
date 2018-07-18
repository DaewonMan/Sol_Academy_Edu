package com.dwm.apr16_1_pw.post;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Reples {
	private List<Reple> reple;
	
	public Reples() {
		// TODO Auto-generated constructor stub
	}

	public Reples(List<Reple> reple) {
		super();
		this.reple = reple;
	}

	public List<Reple> getReple() {
		return reple;
	}
	@XmlElement
	public void setReple(List<Reple> reple) {
		this.reple = reple;
	}
	
	
}
