package com.dwm.daisomanage.first;

import java.util.Scanner;

public class SuppliesMenu {
	/* 사무용품 메인 메뉴 */
	public static void showMenu() {
		Scanner sc = null;
		int num = 0;
		try {
			sc = new Scanner(System.in);

			System.out.println("==========================");
			System.out.println("1. 물품 조회");
			System.out.println("2. 물품 폐기");
			System.out.println("3. 메인메뉴로");
			System.out.println("==========================");
			System.out.print("선택 : ");
			num = sc.nextInt();

			SuppliesController.goToSubMenu(num);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("다시입력");
			showMenu();
		}
	}

	/* 각종 선택 메뉴를 표현하는 메소드들 */
	public static void showInfoMenu() {
			SuppliesController.goToDBWork("조회", null);
	}

	public static void showDelMenu() {
		Scanner sc = null;
		
		int no = 0;
		try {
			System.out.print("폐기할 제품 번호 : ");
			no = sc.nextInt();
			
			Supplies supplies = new Supplies(no, null, 0, 0, 0);
			
			SuppliesController.goToDBWork("폐기", supplies);
		} catch (Exception e) {
			e.printStackTrace();
			SuppliesController.goToMenu();
		}
		
	}

}
