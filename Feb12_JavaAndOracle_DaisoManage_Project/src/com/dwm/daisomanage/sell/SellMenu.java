package com.dwm.daisomanage.sell;

import java.util.Scanner;

public class SellMenu {

	public static void showMenu() {
		Scanner sc = null;
		
		int num = 0;
		try {
			sc = new Scanner(System.in);
			System.out.println("-------------------");
			System.out.println("1. 고객에게 판매");
			System.out.println("2. 총 판매현황");
			System.out.println("3. 총 매출액 및 순이익");
			System.out.println("4. 메인메뉴로");
			System.out.println("-------------------");
			System.out.print(">> 선택 : ");
			num = sc.nextInt();
			
			SellController.goToSubMenu(num);
			
		} catch (Exception e) {
			e.printStackTrace();
			showMenu();
		}
		
	}

	public static void showSell() {
		Scanner sc = null;
		String name = null;
		int sold = 0;
		try {
			sc = new Scanner(System.in);
			System.out.print("고객이 산 제품명 : ");
			name = sc.next();
			
			System.out.print("판매 수량 : ");
			sold = sc.nextInt();
			
			SellItem item = new SellItem(0, name, sold, 0);
			
			SellController.goToDBWork("판매", item);
		
		} catch (Exception e) {
			e.printStackTrace();
			SellController.goToMenu();
		}
		
	}

	public static void showItemInfo() {
		SellController.goToDBWork("조회", null);
	}

	public static void showSalesAndProfit() {
		SellController.goToDBWork("매상", null);
	}
}
