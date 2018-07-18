package com.dwm.daisomanage.first;

import java.util.ArrayList;

import com.dwm.daisomanage.main.MainController;
import com.dwm.daisomanage.main.MainMenu;

public class SuppliesController {
	
	/*가구메뉴를 선택하게 하는 메소드*/
	public static void goToSubMenu(int num) {
		if(num == 1) {
			// 조회
			SuppliesMenu.showInfoMenu();
		} else if(num == 2) {
			// 폐기
			SuppliesMenu.showDelMenu();
		} else if(num == 3) {
			// 메인메뉴로
			MainController.main(null);
		} else {
			// 다시
			goToMenu();
		}
		
	}
	
	/*DB 처리를 하게 하는 메소드*/
	public static void goToDBWork(String what, Supplies s) {
		if(what.equals("조회")) {
			SuppliesDAO.info(s);
		} else if(what.equals("폐기")) {
			SuppliesDAO.del(s);
		} else {
			// 메인메뉴로
			MainMenu.mainMenuShow();
		} 
	}
	
	/*메뉴화면으로 가게하는 메소드들*/
	public static void goToMenu() {
		SuppliesMenu.showMenu();
	}
	
	/*결과 출력 View로 가게하는 메소드들*/
	public static void goToDelResult(String what) {
		SuppliesView.showDelResult(what);
		
	}

	public static void goToInfoResult(String what, ArrayList<Supplies> supplies) {
		SuppliesView.showInfoResult(what, supplies);
	}
}
