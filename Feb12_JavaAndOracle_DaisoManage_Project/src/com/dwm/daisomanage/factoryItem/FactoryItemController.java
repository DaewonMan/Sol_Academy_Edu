package com.dwm.daisomanage.factoryItem;

import java.util.ArrayList;

import com.dwm.daisomanage.main.MainController;

public class FactoryItemController {

	public static void goToMenu() {
		FactoryItemMenu.showMainMenu();
	}

	public static void goToSubMenu(int num) {
		if(num == 1) {
			// 신제품 등록
			FactoryItemMenu.showReg();
		} else if (num == 2) {
			// 기존 제품 생산
			FactoryItemMenu.showUpdate();
		} else if (num == 3) {
			// 다이소 판매
			FactoryItemMenu.showDeal();
		} else if (num == 4) {
			// 제품 현황
			FactoryItemMenu.showInfo();
		} else if (num == 5) {
			// 제품 폐기
			FactoryItemMenu.showDel();
		} else if (num == 6) {
			// 메인 메뉴로
			MainController.main(null);
		} else {
			// 다시
			FactoryItemController.goToMenu();
		}
		
	}

	public static void goToDBWork(String what, FactoryItem item) {
		if(what.equals("등록")) {
			FactoryItemDAO.reg(item);
		} else if(what.equals("생산")) {
			FactoryItemDAO.update(item);
		} else if(what.equals("현황")) {
			FactoryItemDAO.info();
		} else if(what.equals("폐기")) {
			FactoryItemDAO.del(item);
		} else if(what.equals("거래")) {
			FactoryItemDAO.deal(item);
		} else {
			System.out.println("다시 선택");
			FactoryItemController.goToMenu();
		}
		
	}

	public static void goToRegResult(String what) {
		FactoryItemView.showRegResult(what);
	}

	public static void goToUpdateResult(String what) {
		FactoryItemView.showUpdateResult(what);
	}

	public static void goToDelResult(String what) {
		FactoryItemView.showDelResult(what);
	}

	public static void goToInfoResult(String what, ArrayList<FactoryItem> items) {
		FactoryItemView.showInfoResult(what, items);
	}

	public static void goToDealResult(String what) {
		FactoryItemView.showDealResult(what);
	}

}
