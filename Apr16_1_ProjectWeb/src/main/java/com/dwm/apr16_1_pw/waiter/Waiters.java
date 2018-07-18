package com.dwm.apr16_1_pw.waiter;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Waiters {
	private List<Waiter> waiter;
	
	public Waiters() {
		// TODO Auto-generated constructor stub
	}

	public Waiters(List<Waiter> waiter) {
		super();
		this.waiter = waiter;
	}

	public List<Waiter> getWaiter() {
		return waiter;
	}
	@XmlElement
	public void setWaiter(List<Waiter> waiter) {
		this.waiter = waiter;
	}
	
	
}
