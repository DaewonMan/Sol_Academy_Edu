package com.dwm.daisomanage.sell;

import java.util.ArrayList;

public class SellView {

	public static void showSellResult(String what) {
		if(what.equals("성공")) {
			System.out.println("고객에게 상품 판매 성공!!");
		} else {
			System.out.println("고객에게 상품 판매 실패..");			
		}
		SellController.goToMenu();
	}

	public static void showInfoResult(String what, ArrayList<SellItem> items) {
		if(what.equals("성공")) {
			for (SellItem sellItem : items) {
				sellItem.printInfo();
				System.out.println("-------------------");
			}
			System.out.println("상품 조회 성공!!");
		} else {
			System.out.println("상품 조회 실패..");			
		}
		SellController.goToMenu();
	}

	public static void showSalesResult(String what, int tempSale) {
		if(what.equals("성공")) {
			System.out.println("총 매상 : " + tempSale);
			System.out.println("순 이익 : " + (double)(tempSale*2)/3);
			System.out.println("총 매상 및 순 이익 산출 성공!!");
		} else {
			System.out.println("총 매상 및 순 이익 산출 실패..");			
		}
		SellController.goToMenu();
	}

}
