package com.dwm.HW.coordinate;

public class Area {
	private String name;
	private int x;
	private int y;
	public Area(String name, int x, int y) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void print() {
		System.out.println(name);
		System.out.println(x);
		System.out.println(y);
	}
}
