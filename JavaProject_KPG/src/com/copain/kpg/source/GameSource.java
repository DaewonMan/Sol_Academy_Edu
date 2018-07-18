package com.copain.kpg.source;

public class GameSource {
	public static int card[];
	public static int myCard[];
	public static int yourCard[];
	public int win;
	public int draw;
	public int lose;
	public int batting;
	public int mbm;
	public int ybm;

	private static final GameSource GS = new GameSource();

	public GameSource() {
		card = new int[20];
		myCard = new int[2];
		yourCard = new int[2];
		win = 0;
		draw = 0;
		lose = 0;
		batting = 0;
		mbm = 0;
		ybm = 0;
	}

	// 캡슐화!!!
	public static GameSource getGS() {
		return GS;
	}

	// 점수를 받아 승패여부를 result값으로 리턴한다.
	public int sorceCheck(int myScore, int yourScore) {
		if (myScore > yourScore) {
			System.out.println(" 승리! ");
			return 1;
		} else if (myScore == yourScore) {
			System.out.println(" 비겼습니다 ");
			return 2;
		} else {
			System.out.println(" 패배! ");
			return 3;
		}
	}

	// 플레이어의 카드에 따라 점수 부여
	public static int getScore(int[] playerCard) {

		int player = 0;
		// score check
		if ((playerCard[0] == 3 && playerCard[1] == 8) || (playerCard[0] == 8 && playerCard[1] == 3)) {
			player = 200; // 38광땡
		}
		if (playerCard[0] == 1 && (playerCard[1] == 3 || playerCard[1] == 8)) {
			player = 90; // 광땡 90점

		}
		if ((playerCard[0] == 8) && (playerCard[1] == 1)) {
			player = 90;
		}
		if ((playerCard[0] == 3) && (playerCard[1] == 1)) {
			player = 90;

		}
		if (playerCard[0] % 10 == playerCard[1] % 10) {
			player = 80; // 땡
		}

		if (playerCard[0] == 1 || playerCard[0] == 11) {
			if (playerCard[1] == 2 || playerCard[1] == 12) {
				player = 70; // 알리 (1월, 2월)
			} else if (playerCard[1] == 4 || playerCard[1] == 14) {
				player = 65; // 독사 (1월, 4월)
			} else if (playerCard[1] == 9 || playerCard[1] == 19) {
				player = 60; // 구삥 (1월, 9월)
			} else if (playerCard[1] == 10 || playerCard[1] == 20) {
				player = 55; // 장삥 (1월, 10월)
			}
		}
		if (playerCard[0] == 2 || playerCard[0] == 12) {
			if (playerCard[1] == 1 || playerCard[1] == 11)
				player = 70;
		}
		if (playerCard[0] == 4 || playerCard[0] == 14) {
			if (playerCard[1] == 1 || playerCard[1] == 11) {
				player = 65;
			} else if (playerCard[1] == 10 || playerCard[1] == 20) {
				player = 50; // 장사 (4월,10월)
			} else if (playerCard[1] == 6 || playerCard[1] == 16) {
				player = 45; // 세륙 (4월,6월)
			}
		}
		if (playerCard[0] == 9 || playerCard[0] == 19) {
			if (playerCard[1] == 1 || playerCard[1] == 11)
				player = 60;
		}
		if (playerCard[0] == 10 || playerCard[0] == 20) {
			if (playerCard[1] == 1 || playerCard[1] == 11) {
				player = 55;
			} else if (playerCard[1] == 4 || playerCard[1] == 14) {
				player = 50;
			}
		}
		if (playerCard[0] == 6 || playerCard[0] == 16) {
			if (playerCard[1] == 4 || playerCard[1] == 14)
				player = 45;
		} else {
			player = (playerCard[0] + playerCard[1]) % 10;

		}
		return player;
	}

	// 유저와 컴퓨터에게 카드를 분배한다.
	public static void getCard(int[] myCard, int[] yourCard, int[] card) {
		int temp;
		// card배열은 이미 shuffle되어 있어서 순서대로 주어도
		// 랜덤한 카드를 받음
		myCard[0] = card[0];
		yourCard[0] = card[1];
		myCard[1] = card[2];
		yourCard[1] = card[3];
	}

	public void batting(int mbm, int batting) {

		int b = 0;
		int bb = 0;
		int bbb = 0;
		int bbbb = 0;
		if (batting == 1) {
			b = 3 * mbm;
			System.out.printf("따당!!  %d 원\n", b);
		} else if (batting == 2) {
			bb = 2 * mbm;
			System.out.printf("콜!!  %d 원\n", bb);
		} else if (batting == 3) {
			bbb = 1 * mbm;
			System.out.printf("체크!!  %d 원\n", bbb);
			System.out.println(bbb);
		} else if (batting == 4) {
			bbbb = mbm;
			System.out.printf("%d 원 잃었습니다\n", mbm);

		} else {
			System.out.println("입력오류");
			return;
		}
	}

	public void cardSwap(int[] playerCard) {
		int temp;

		temp = playerCard[0];
		playerCard[0] = playerCard[1];
		playerCard[1] = temp;
	}

	// 카드 섞기
	public static void shuffle(int[] card) {
		boolean swit[] = new boolean[card.length];
		int w = 0;
		int r;

		// random
		while (w < card.length) {
			r = (int) (Math.random() * card.length);
			if (!swit[r]) {
				swit[r] = true;
				card[w] = r + 1; // 1~20 (r%10)
				w++;
			}

		}
	}

	public void confirmFirstCard(int[] myCard, int[] yourCard, int[] card) {
		if (myCard[0] == 1 || myCard[0] == 3 || myCard[0] == 8) {
			System.out.println("My card: " + myCard[0] + "광! "); // + myCard[1] + "월");
		} else if (card[0] > 10) {
			System.out.println("My card: " + (myCard[0] = card[0] - 10) + "월 "); // + myCard[1] + "월");
		} else {
			System.out.println("My card: " + myCard[0] + "월 "); // + myCard[1] + "월");
		}
		if (yourCard[0] == 1 || yourCard[0] == 3 || yourCard[0] == 8) {
			System.out.println("Your card: " + yourCard[0] + "광! ");// + yourCard[1] + "월");
		} else if (card[1] > 10) {
			System.out.println("Your card: " + (yourCard[0] = card[1] - 10) + "월 ");// + yourCard[1] + "월");
		} else {
			System.out.println("Your card: " + yourCard[0] + "월 ");// + yourCard[1] + "월");
		}
		System.out.println("=========================");
	}

	public void confirmSecondCard(int[] myCard, int[] yourCard, int[] card) {
		if (myCard[1] == 1 || myCard[1] == 3 || myCard[1] == 8) {
			System.out.println("My card: " + myCard[1] + "광! "); // + myCard[1] + "월");
		} else if (card[2] > 10) {
			System.out.println("My card: " + (myCard[1] = card[2] - 10) + "월 "); // + myCard[1] + "월");
		} else {
			System.out.println("My card: " + myCard[1] + "월 "); // + myCard[1] + "월");
		}
		if (yourCard[1] == 1 || yourCard[1] == 3 || yourCard[1] == 8) {
			System.out.println("Your card: " + yourCard[1] + "광! ");// + yourCard[1] + "월");
		} else if (card[3] > 10) {
			System.out.println("Your card: " + (yourCard[1] = card[3] - 10) + "월 ");// + yourCard[1] + "월");
		} else {
			System.out.println("Your card: " + yourCard[1] + "월 ");// + yourCard[1] + "월");
			System.out.println("=========================");
		}
	}

	/*
	 * public void main(String[] args) {
	 * 
	 * 
	 * 
	 * String again = "y"; Scanner keyboard = new Scanner(System.in);
	 * 
	 * while (again.equals("y") || again.equals("yes") || again.equals("Y") ||
	 * again.equals("Yes")) { try { System.out.println("배팅 하세요");
	 * System.out.print("배팅금 : "); mbm = keyboard.nextInt();
	 * 
	 * System.out.printf("%d 원 배팅!\n", mbm); Thread.sleep(100);
	 * 
	 * System.out.println("Shuffling now..."); shuffle(card); // 카드 섞기
	 * 
	 * // 카드 분배 // 섞인 카드를 유저와 컴퓨터가 1장씩 나눠 가진다. getCard(myCard, yourCard, card);
	 * 
	 * // 유저 카드, 컴퓨터 카드 출력 System.out.println("Open Cards");
	 * 
	 * confirmFirstCard(myCard, yourCard, card); // 첫패 까기
	 * 
	 * while(true) { System.out.println("1. 따당"); System.out.println("2. 콜");
	 * System.out.println("3. 체크"); System.out.println("4. 다이");
	 * System.out.print("선택 : "); batting = keyboard.nextInt();
	 * System.out.println("========================="); if(batting < 1 || batting >
	 * 4) { System.out.println("다시 입력하시오!!"); continue; } else { break; } } if
	 * (batting == 4) { lose++; //break; continue; }
	 * 
	 * batting(mbm, batting); System.out.println("=========================");
	 * getCard(myCard, yourCard, card); // 카드를 또 한장씩 가져온다
	 * 
	 * confirmSecondCard(myCard, yourCard, card); // 두번째 카드
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } // 점수를 가져온다. // 38광땡 = 200, 광땡
	 * = 90, ... 등등 int myScore = getScore(myCard); int yourScore =
	 * getScore(yourCard);
	 * 
	 * // 가져온 점수를 비교해서 승패를 나눈다.
	 * 
	 * // result == 1이면 승리, 2이면 무승부, 3이면 패배 int result = sorceCheck(myScore,
	 * yourScore); if (result == 1) win++; else if (result == 2) draw++; else
	 * lose++;
	 * 
	 * System.out.println(win + "승 " + draw + "무 " + lose + "패");
	 * System.out.print("한판 더! :  ");
	 * 
	 * again = keyboard.next(); }
	 * 
	 * System.out.println("게임 종료");
	 * 
	 * }
	 */
}