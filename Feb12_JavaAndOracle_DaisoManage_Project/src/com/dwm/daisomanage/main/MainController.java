package com.dwm.daisomanage.main;

import com.dwm.daisomanage.factoryItem.FactoryItemController;
import com.dwm.daisomanage.first.SuppliesController;
import com.dwm.daisomanage.sell.SellController;

public class MainController {
	public static void mainController(int num) {
		if(num == 1) {
			// 거래처 관련 업무
			FactoryItemController.goToMenu();
		} else if(num == 2) {
			// 재고 관련 업무
			SuppliesController.goToMenu();
		} else if(num == 3) {
			// 판매 관련 업무
			SellController.goToMenu();
		} else if(num == 4) {
			// 끝내기
			
		} else {
			// 이외의 값
			main(null);
		}
		
	}
	public static void main(String[] args) {
		MainMenu.mainMenuShow(); // 메뉴출력
	}
}
