package com.dwm.apr16_1_pw.post;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Posts {
	private List<Post> post;
	
	public Posts() {
		// TODO Auto-generated constructor stub
	}

	public Posts(List<Post> post) {
		super();
		this.post = post;
	}

	public List<Post> getPost() {
		return post;
	}
	@XmlElement
	public void setPost(List<Post> post) {
		this.post = post;
	}
	
	
}
