package com.dwm.daisomanage.main;

import java.util.Scanner;

// V
public class MainMenu {
	public static void mainMenuShow() {
		Scanner sc = null;
		int num = 0;
		try {
			sc = new Scanner(System.in);
			
			System.out.println("========다이소통합정보관리=========");
			System.out.println("1. 거래처 관련 업무");
			System.out.println("2. 재고 관련 업무");
			System.out.println("3. 판매 관련 업무");
			System.out.println("4. 끝내기");
			System.out.println("=======================================");
			System.out.print(">> 메뉴선택 : ");
			
			num = sc.nextInt();
			
			MainController.mainController(num);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("다시입력");
			mainMenuShow();
		}
	}
}
