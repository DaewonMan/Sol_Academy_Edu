package com.dwm.daisomanage.factoryItem;

import java.util.ArrayList;

public class FactoryItemView {

	public static void showRegResult(String what) {
		if(what.equals("성공")) {
			System.out.println("신상품 등록 성공!!");
		} else {
			System.out.println("신상품 등록 실패!!");			
		}
		FactoryItemController.goToMenu();
	}

	public static void showUpdateResult(String what) {
		if(what.equals("성공")) {
			System.out.println("기존 제품 재생산 성공!!");
		} else {
			System.out.println("기존 제품 재생산 실패!!");			
		}
		FactoryItemController.goToMenu();
	}

	public static void showDelResult(String what) {
		if(what.equals("성공")) {
			System.out.println("해당 상품 폐기 성공!!");
		} else {
			System.out.println("해당 상품 폐기 실패!!");			
		}
		FactoryItemController.goToMenu();
	}

	public static void showInfoResult(String what, ArrayList<FactoryItem> items) {
		if(what.equals("성공")) {
			for (FactoryItem factoryItem : items) {
				factoryItem.printInfo();
				System.out.println("--------------");
			}
			System.out.println("모든 상품 조회 성공!!");
		} else {
			System.out.println("모든 상품 조회 실패!!");			
		}
		FactoryItemController.goToMenu();
	}

	public static void showDealResult(String what) {
		if(what.equals("성공")) {
			System.out.println("거래 성공!!");
		} else {
			System.out.println("거래 실패!!");			
		}
		FactoryItemController.goToMenu();
	}
}
