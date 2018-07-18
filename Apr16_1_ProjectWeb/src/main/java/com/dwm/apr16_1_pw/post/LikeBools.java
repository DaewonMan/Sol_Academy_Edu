package com.dwm.apr16_1_pw.post;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LikeBools {
	private List<LikeBool> likebool;

	public LikeBools() {
		// TODO Auto-generated constructor stub
	}

	public LikeBools(List<LikeBool> likebool) {
		super();
		this.likebool = likebool;
	}

	public List<LikeBool> getLikebool() {
		return likebool;
	}
	@XmlElement
	public void setLikebool(List<LikeBool> likebool) {
		this.likebool = likebool;
	}
	
	
}
