package com.copain.kpg.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.copain.kpg.board.Board;
import com.copain.kpg.source.GameSource;

public class SMain {
	public static void main(String[] args) {
		ServerSocket ss = null;
		
		try {
			ss = new ServerSocket(1111); // 각자 ip 맞는 포트번호
			System.out.println("대기중");
			Socket s = ss.accept(); // 클라이언트 받아들임
			System.out.println("상대방이 접속 하였습니다!");

			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			OutputStream os = s.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);

			Board b = new Board();
			b.mainDisplay();
			b.setSocket(s);
			b.whoFirst = true; // 서버가 선 턴
			//GameSource gs = GameSource.getGS(); // 싱글턴
		
			
			new Thread() {
				public void run() {
					String str1 = null;
					while (true) {
						try {
							str1 = br.readLine();
							if (str1.equals("Ready...") && b.jb.getText().equals("Ready...")) {
								// 둘다 레디 박으면 게임시작
								b.startGame();
								b.otherCnt++;

							} else if(str1.equals("베팅금입력")) {
								int you_money = Integer.parseInt(br.readLine());
								b.ta.setText("상대방이 " + you_money +"원 입력하였습니다.");
								b.bet_amount1 = you_money;
								b.yourCard[0] = Integer.parseInt(br.readLine());
								b.myCard[0] = Integer.parseInt(br.readLine());
								
								b.updateMoney(b.bet_amount1);
								b.mCard_label1.setIcon(b.ci.il[b.myCard[0]]); // 패 이미지 삽입
								b.yCard_label1.setIcon(b.ci.il[b.yourCard[0]]);
								
								b.secondBetting();
							} else if(str1.equals("베팅조건입력")) {
								int bet_result = Integer.parseInt(br.readLine());
								
								if (bet_result == 0) {
									b.ta.setText("상대가 따당을 베팅하였습니다.");

									b.bet_result = JOptionPane.showOptionDialog(null, "베팅옵션을 선택하세요!", "베팅옵션선택",
											JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, b.bet_button0, "다이");

									if (bet_result != 0)
										b.ta.setText("다이를 베팅하였습니다.");
									else
										b.bet_amount1 *= 3;

								} else if (bet_result == 1) {
									b.ta.setText("상대가 콜을 베팅하였습니다.");

									b.bet_result = JOptionPane.showOptionDialog(null, "베팅옵션을 선택하세요!", "베팅옵션선택",
											JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, b.bet_button1, "다이");

									if (bet_result != 0)
										b.ta.setText("다이를 베팅하였습니다.");
									else
										b.bet_amount1 *= 2;

								} else if (bet_result == 2) {
									b.ta.setText("상대가 체크를 베팅하였습니다.");
									b.bet_result = JOptionPane.showOptionDialog(null, "베팅옵션을 선택하세요!", "베팅옵션선택",
											JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, b.bet_button2, "다이");

									if (bet_result != 0)
										b.ta.setText("다이를 베팅하였습니다.");

								} else {
									// 상대가 다이한 경우
									b.ta.setText("상대가 다이를 베팅하였습니다.!!!?");
									System.out.println(bet_result);
								}
								// 따당, 콜, 체크인 경우 가격 변동
								if (bet_result == 0) {
									//System.out.println(bet_amount1); // 0
									b.updateMoney(b.bet_amount1);
								}

								b.yourCard[1] = Integer.parseInt(br.readLine());
								b.yCard_label2.setIcon(b.ci.il[b.yourCard[1]]); // 패 이미지 삽입
								
								b.myCard[1] = Integer.parseInt(br.readLine());
								b.mCard_label2.setIcon(b.ci.il[b.myCard[1]]); // 패 이미지 삽입
								
								b.judgeResult();
							} /*else {
								JOptionPane.showMessageDialog(null, "상대방이 베팅하지 않았습니다.");
								return; // 함수끝내기
							}*/
							
							
							/*else if (str1.equals("2") && b.receiveCnt == 1) {
								// 선턴인 상대가 먼저 배팅누르고 나는 아직 안눌렀을때
								b.letYouGo = true;
								//b.card1 = gs.yourCard[0];
								str1 = br.readLine(); // 상대방이 보낸 베팅금액 할당
								b.bet_amount1 = Integer.parseInt(str1);
								b.otherCnt++;
								
								b.yourCard[0] = Integer.parseInt(br.readLine());
								b.myCard[0] = Integer.parseInt(br.readLine());
								

							} else if (str1.equals("3") && b.receiveCnt == 2) {
								// 선턴인 상대가 2차 배팅누르고 나는 아직 안눌렀을때
								b.letYouGo = true;
								str1 = br.readLine(); // 상대방이 보낸 베팅금액 할당
								b.bet_result = Integer.parseInt(str1);
								b.otherCnt++;
								//b.card1 = gs.yourCard[1];
								b.yourCard[1] = Integer.parseInt(br.readLine());
								b.myCard[1] = Integer.parseInt(br.readLine());
							}*/
							
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				};
			}.start();
			String str2 = null;
			while (true) {
				if (b.receiveCnt <= 1) {
					str2 = b.jb.getText();
					bw.write(str2 + "\r\n");
					bw.flush();

				} /*else if (b.receiveCnt == 2 ) {
					bw.write(b.receiveCnt + "\r\n"); // 상대편에게 베팅한것을 알리기위해 버튼누른 횟수 보냄
					bw.flush();
					bw.write(b.bet_amount1 + "\r\n"); // 상대편에게 베팅 금액을 알리기 위해
					bw.flush();
					//bw.write(GameSource.myCard[0]);
					//bw.flush();
					//bw.write(GameSource.yourCard[0]);
					//bw.flush();
				} else if (b.receiveCnt == 3 ) {
					bw.write(b.receiveCnt + "\r\n"); // 상대편에게 베팅한것을 알리기위해 버튼누른 횟수 보냄
					bw.flush();
					bw.write(b.bet_result + "\r\n"); // 상대편에게 2차 베팅 결과보내기위해
					bw.flush();
					//bw.write(GameSource.myCard[1]);
					//bw.flush();
					//bw.write(GameSource.yourCard[1]);
					//bw.flush();
				}*/

			}
		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {

			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
