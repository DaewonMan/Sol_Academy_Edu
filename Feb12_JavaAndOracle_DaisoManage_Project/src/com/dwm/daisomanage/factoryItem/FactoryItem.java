package com.dwm.daisomanage.factoryItem;

public class FactoryItem {
	private int no;
	private String name;
	private int amount;
	private int cost; // 원가
	
	public FactoryItem() {
		// TODO Auto-generated constructor stub
	}

	public FactoryItem(int no, String name, int amount, int cost) {
		super();
		this.no = no;
		this.name = name;
		this.amount = amount;
		this.cost = cost;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	public void printInfo() {
		System.out.println("제품번호 : " + no);
		System.out.println("제품명 : " + name);
		System.out.println("생산량 : " + amount);
		System.out.println("원가 : " + cost);
	}
}
