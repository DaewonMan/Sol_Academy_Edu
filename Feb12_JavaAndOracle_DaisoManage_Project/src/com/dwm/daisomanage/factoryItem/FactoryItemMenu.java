package com.dwm.daisomanage.factoryItem;

import java.util.Scanner;

public class FactoryItemMenu {

	public static void showMainMenu() {
		Scanner sc = null;

		int num = 0;
		try {
			sc = new Scanner(System.in);

			System.out.println("-----공장 관리-----");
			System.out.println("1. 신제품 등록"); // 다이소에도 물품등록 되게 한다.
			System.out.println("2. 기존 제품 생산");
			System.out.println("3. 다이소에 판매"); // 다이소에 물품 수량을 추가한다.
			System.out.println("4. 제품 현황");
			System.out.println("5. 제품 폐기");
			System.out.println("6. 메인메뉴로");
			System.out.println("-------------------");
			System.out.print(">> 선택 : ");
			num = sc.nextInt();

			FactoryItemController.goToSubMenu(num);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("다시");
			showMainMenu();
		}

	}

	public static void showReg() {
		Scanner sc = null;

		String name = null;
		int amount = 0, cost = 0;
		try {
			sc = new Scanner(System.in);

			System.out.print("신제품명 : ");
			name = sc.next();

			System.out.print("생산량 : ");
			amount = sc.nextInt();

			System.out.print("원가 : ");
			cost = sc.nextInt();

			FactoryItem item = new FactoryItem(0, name, amount, cost);

			FactoryItemController.goToDBWork("등록", item);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("다시");
			showReg();
		}
	}

	public static void showUpdate() {
		Scanner sc = null;
		
		String name = null;
		int amount = 0;
		try {
			sc = new Scanner(System.in);

			System.out.print("제품명 : ");
			name = sc.next();

			System.out.print("생산량 : ");
			amount = sc.nextInt();

			FactoryItem item = new FactoryItem(0, name, amount, 0);

			FactoryItemController.goToDBWork("생산", item);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("다시");
			showUpdate();
		}

	}

	public static void showInfo() {
		FactoryItemController.goToDBWork("현황", null);
	}

	public static void showDel() {
		Scanner sc = null;
		
		String name = null;
		int no = 0;
		try {
			sc = new Scanner(System.in);

			System.out.print("제품명 : ");
			name = sc.next();

			FactoryItem item = new FactoryItem(0, name, 0, 0);

			FactoryItemController.goToDBWork("폐기", item);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("다시");
			showDel();
		}
		
	}

	public static void showDeal() {
		Scanner sc = null;
		
		String name = null;
		int amount = 0;
		try {
			sc = new Scanner(System.in);

			System.out.print("거래할 제품명 : ");
			name = sc.next();
			
			System.out.print("거래할 제품 수량 : ");
			amount = sc.nextInt();

			FactoryItem item = new FactoryItem(0, name, amount, 0);

			FactoryItemController.goToDBWork("거래", item);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("다시");
			showDeal();
		}
		
	}
}
