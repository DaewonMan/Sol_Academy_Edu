package com.dwm.daisomanage.sell;

public class SellItem {
	private int no;
	private String name;
	private int sold;
	private int sum;
	
	public SellItem() {
		// TODO Auto-generated constructor stub
	}

	public SellItem(int no, String name, int sold, int sum) {
		super();
		this.no = no;
		this.name = name;
		this.sold = sold;
		this.sum = sum;
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

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}
	public void printInfo() {
		System.out.println("제품번호 : " + no);
		System.out.println("제품명 : " + name);
		System.out.println("판매량 : " + sold);
		//System.out.println("매출액 : " + sum);
	}
	
}
