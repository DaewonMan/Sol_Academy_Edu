package com.dwm.kpg.main;

import com.copain.kpg.board.Board;

public class KPGMain {
	public static void main(String[] args) {
		/* 1 서로 연결 확인 */
		// 1. 서버를 열고
		// 2. 클라이언트 입장

		/* 2 */
		// 3. 게임시작, 기본급 지급
		// 4. 1차 배팅을한다.(돈을 낸다)
		// 5. 한장씩 패를 나누고
		// 6. 서로 패를 확인하고

		/* 3 */
		// 7. 2차 배팅(버튼선택)
		// 8. 따당(1차배팅금 3배 추가납부)
		// 콜(1차 배팅금의 2배 추가납부)
		// 체크(1차 배팅금 만큼 추가납부)
		// 다이(1차배팅금 잃고 게임종료)
		// 9. 패가 섞이고 한장씩 가진다.
		// 10 패에 조합에 따라 승패 갈림
		// 11. 배팅금 지급

		Board b = new Board();
		b.mainDisplay();
		

	}
}
