package com.dwm.daisomanage.first;

public class Supplies {
	private int no;
	private String name;
	private int price;
	private int amount; // 熱榆
	private int floor;
	
	public Supplies() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public int getFloor() {
		return floor;
	}



	public void setFloor(int floor) {
		this.floor = floor;
	}



	public Supplies(int no, String name, int price, int amount, int floor) {
		super();
		this.no = no;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.floor = floor;
	}



	public void printInfo() {
		
		System.out.println("剩幗 : " + no);
		System.out.println("鼻ヶ貲 : " + name);
		System.out.println("陛問 : " + price);
		System.out.println("偎熱 : " + amount);
		System.out.println("類熱 : " + floor + "類");
		
	}
}
