package com.dwm.daisomanage.first;

import java.util.ArrayList;

public class SuppliesView {

	public static void showInfoResult(String what, ArrayList<Supplies> supplies) {
		if(what.equals("성공")) {
			for (Supplies tempS : supplies) {
				tempS.printInfo();
				System.out.println("-----------------");
			}
			System.out.println("상품 조회 성공!!");
		} else {
			System.out.println("상품 조회 실패!!");			
		}
		SuppliesController.goToMenu();
	}

	public static void showDelResult(String what) {
		if(what.equals("성공")) {
			System.out.println("상품 삭제 성공!!");
		} else {
			System.out.println("상품 삭제 실패..");
		}
		SuppliesController.goToMenu();
		
	}
}
