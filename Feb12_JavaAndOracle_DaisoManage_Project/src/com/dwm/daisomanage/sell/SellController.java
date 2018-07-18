package com.dwm.daisomanage.sell;

import java.util.ArrayList;

import com.dwm.daisomanage.main.MainController;

public class SellController {

	public static void goToMenu() {
		SellMenu.showMenu();
	}

	public static void goToSubMenu(int num) {
		if(num == 1) {
			// 고객에게 판매
			SellMenu.showSell();
		} else if (num == 2) {
			// 선택상품 판매현황
			SellMenu.showItemInfo();
		} else if (num == 3) {
			// 총 매출액 및 순이익
			SellMenu.showSalesAndProfit();
		} else if(num == 4) {
			// 메인메뉴로
			MainController.main(null);
		} else {
			// 다시
			goToMenu();
		}
		
	}

	public static void goToDBWork(String what, SellItem item) {
		if(what.equals("판매")) {
			SellDAO.sell(item);
		} else if(what.equals("조회")) {
			SellDAO.info();
		} else if(what.equals("매상")) {
			SellDAO.sales();
		} else {
			System.out.println("다시선택");
			goToMenu();
		}
		
	}

	public static void goToSellResult(String what) {
		SellView.showSellResult(what);
	}

	public static void goToInfoResult(String what, ArrayList<SellItem> items) {
		SellView.showInfoResult(what, items);
	}

	public static void goToSalesResult(String what, int tempSale) {
		SellView.showSalesResult(what, tempSale);
	}

}
