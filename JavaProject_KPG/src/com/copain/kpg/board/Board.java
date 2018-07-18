package com.copain.kpg.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.copain.kpg.source.Card;
import com.copain.kpg.source.GameSource;

public class Board implements ActionListener {

	JFrame jf;
	public JButton jb, exit;
	public JPanel my_money, bet, back_ground, you_card1, you_card2, my_card1, my_card2;
	public JLabel my_label, bet_label, yCard_label1, yCard_label2, mCard_label1, mCard_label2;

	JPanel currentDP, meDP;
	JLabel currentDL, meDL;

	public JTextArea ta;

	public int basic_money1, basic_money2;
	public int bet_amount1, bet_amount2;
	public int receiveCnt, otherCnt;
	public int bet_sum;
	public int bet_result;

	public String[] bet_buttons;
	public String[] bet_button0;
	public String[] bet_button1;
	public String[] bet_button2;

	public boolean whoFirst;
	public boolean letYouGo;

	// 족보 클래스 변수 및 속성
	GameSource gs;

	public int card[];
	public int myCard[];
	public int yourCard[];
	public int win;
	public int draw;
	public int lose;
	public int batting;
	public int mbm;
	public int ybm;
	
	public Card ci; // 패 이미지 객체
	
	public int card1, card2;
	
	Socket s;
	OutputStream os;
	OutputStreamWriter osw;
	BufferedWriter bw;

	public Board() {
		basic_money1 = 10000; // 처음 입장시 가지는 금액
		basic_money2 = 10000; // 상대편의 처음 입장시 가지는 금액
		bet_amount1 = 0; // 베팅금액
		bet_amount2 = 0; // 상대편 베팅금액
		receiveCnt = 0; // 패 받은 횟수
		otherCnt = 0;
		bet_sum = 0; // 총 배팅 금액

		bet_buttons = new String[] { "따당(3배)", "콜(2배)", "체크(1배)", "다이" }; // 베팅옵션
		bet_button0 = new String[] { "따당(3배)", "다이" }; // 베팅옵션
		bet_button1 = new String[] { "콜(2배)", "다이" }; // 베팅옵션
		bet_button2 = new String[] { "체크(1배)", "다이" }; // 베팅옵션

		whoFirst = false; // 누가 먼저 시작할지 알아내는 변수
		letYouGo = false; // 선을 잡은 상대가 배팅했다는 신호를 알리는 변수
		bet_result = 0;
		
		gs = GameSource.getGS(); // 족보 클래스 싱글턴
		
		card = new int[20];
		myCard = new int[2];
		yourCard = new int[2];
		win = 0;
		draw = 0;
		lose = 0;
		batting = 0;
		mbm = 0;
		ybm = 0;
		
		ci = new Card();
		card1 = 0;
		card2 = 0;
		
	}

	public void mainDisplay() {
		// 화면 틀
		JFrame jf = new JFrame("Korean Poker Game made by copain"); // 프레임 생성
		jf.setLayout(null);
		jf.setBounds(270, 130, 900, 600);
		jf.setResizable(false);
		jf.setVisible(true);

		// 패들
		// 상대패
		you_card1 = new JPanel();
		you_card1.setBounds(250, 70, 70, 105);
		you_card1.setBackground(Color.red);

		yCard_label1 = new JLabel();
		// yCard_label1.setBackground(Color.red);
		you_card1.add(yCard_label1);
		jf.add(you_card1);

		you_card2 = new JPanel();
		you_card2.setBounds(330, 70, 70, 105);
		you_card2.setBackground(Color.red);

		yCard_label2 = new JLabel();
		// yCard_label2.setBackground(Color.red);
		you_card2.add(yCard_label2);
		jf.add(you_card2);

		// 내패
		my_card1 = new JPanel();
		my_card1.setBounds(250, 400, 70, 105);
		my_card1.setBackground(Color.red);

		mCard_label1 = new JLabel();
		//mCard_label1.setBackground(Color.red);
		my_card1.add(mCard_label1);
		jf.add(my_card1);

		my_card2 = new JPanel();
		my_card2.setBounds(330, 400, 70, 105);
		my_card2.setBackground(Color.red);

		mCard_label2 = new JLabel();
		// mCard_label2.setBackground(Color.red);
		my_card2.add(mCard_label2);
		jf.add(my_card2);

		// 현재 금액 - 이름 표시 패널
		currentDP = new JPanel();
		currentDP.setBounds(660, 80, 220, 30);
		currentDP.setBackground(Color.black);

		currentDL = new JLabel("현재금액");
		currentDL.setBounds(30, 30, 50, 40);
		currentDL.setForeground(Color.white);
		currentDL.setFont(new Font("돋움", Font.BOLD, 20));
		currentDP.add(currentDL);
		jf.add(currentDP);

		// **현재 금액 - 표시 패널
		my_money = new JPanel();
		my_money.setBounds(660, 111, 220, 40);
		my_money.setBackground(Color.gray);
		// jf.add(jp);

		my_label = new JLabel();
		my_label.setBounds(30, 30, 50, 40);
		my_label.setForeground(Color.white);
		my_label.setFont(new Font("돋움", Font.BOLD, 20));
		my_money.add(my_label);
		jf.add(my_money);

		// 처음 입장시 현재 금액 라벨에 표시
		my_label.setText(basic_money1 + "");

		// 판돈 - 표시명 패널
		meDP = new JPanel();
		meDP.setBounds(660, 200, 220, 30);
		meDP.setBackground(Color.black);

		meDL = new JLabel("판 돈");
		meDL.setForeground(Color.white);
		meDL.setFont(new Font("돋음", Font.BOLD, 20));
		meDP.add(meDL);
		jf.add(meDP);

		// ** 판돈 - 표시 패널
		bet = new JPanel();
		bet.setBounds(660, 231, 220, 40);
		bet.setBackground(Color.darkGray);

		bet_label = new JLabel();
		// bet_label.setBounds(0, 50, 30, 20);
		bet_label.setForeground(Color.white);
		bet_label.setFont(new Font("돋움", Font.BOLD, 20));
		bet.add(bet_label);
		jf.add(bet);

		// 상태를 출력하는 창
		ta = new JTextArea(); // 글자적는 칸인데 글자를 여러줄 쓸수잇음
		ta.setBounds(660, 350, 220, 90);
		ta.setEditable(false);
		jf.add(ta);

		// 배팅 버튼
		jb = new JButton("Start?");
		jb.setFont(new Font("돋움", Font.BOLD, 35));
		jb.setBounds(680, 450, 200, 70);
		jb.addActionListener(this);
		jf.add(jb);

		// 임시 나가기 버튼
		exit = new JButton("나가기");
		exit.setFont(new Font("돋움", Font.BOLD, 20));
		exit.setBounds(780, 20, 100, 50);
		exit.addActionListener(this);
		jf.add(exit);

		// 패 놓을 바닥
		back_ground = new JPanel();
		back_ground.setBounds(20, 20, 620, 520);
		back_ground.setBackground(Color.green);
		jf.add(back_ground);

	}
	// 소켓 연결
	public void setSocket(Socket s) {
		try {
			this.s = s;
			os = s.getOutputStream();
			osw = new OutputStreamWriter(os);
			bw = new BufferedWriter(osw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 게임시작을 알리는 함수
	public void startGame() {
		receiveCnt = 2; // 2
		JOptionPane.showMessageDialog(null, "게임이 시작되었습니다!!!");
		jb.setText("베 팅");
		ta.setText("Start Game!!!");
		
		if (whoFirst) {
			bet_amount2 = bet_amount1 = Integer.parseInt(JOptionPane.showInputDialog("베팅금액을 입력하시오"));
			updateMoney(bet_amount1);
			ta.setText(bet_amount1 + "원을 베팅했습니다.");
			
			mCard_label1.setIcon(ci.il[GameSource.myCard[0]]); // 패 이미지 삽입
			yCard_label1.setIcon(ci.il[GameSource.yourCard[0]]);
			try {
				bw.write("베팅금입력\r\n");
				bw.flush();
				bw.write(bet_amount1 + "\r\n");
				bw.flush();
				bw.write(GameSource.myCard[0] + "\r\n");
				bw.flush();
				bw.write(GameSource.yourCard[0] + "\r\n");
				bw.flush();
				
				JOptionPane.showMessageDialog(null, ta.getText());
				secondBetting();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			ta.setText("상대방이 베팅금 입력중...");
		}
	}
	
	public void secondBetting() {
		if(whoFirst) {
			bet_result = JOptionPane.showOptionDialog(null, "베팅옵션을 선택하세요!", "베팅옵션선택",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, bet_buttons, "콜(2배)");
			if (bet_result == 0) {
				// 따당인 경우
				ta.setText("따당을 베팅하였습니다.");
				bet_amount1 *= 3; // 기본금에 3배
			} else if (bet_result == 1) {
				// 콜인 경우
				ta.setText("콜을 베팅하였습니다.");
				bet_amount1 *= 2; // 기본금에 2배
			} else if (bet_result == 2) {
				ta.setText("체크를 베팅하였습니다.");
			} else {
				ta.setText("다이를 베팅하였습니다.");
			}

			// 따당, 콜, 체크인 경우 가격 변동
			if (bet_result <= 2) {
				updateMoney(bet_amount1);
			}
			mCard_label2.setIcon(ci.il[GameSource.myCard[1]]); // 패 이미지 삽입
			yCard_label2.setIcon(ci.il[GameSource.yourCard[1]]); // 패 이미지 삽입
			
			try {
				bw.write("베팅조건입력" + "\r\n");
				bw.flush();
				bw.write(bet_result + "\r\n");
				bw.flush();
				bw.write(GameSource.myCard[1] + "\r\n");
				bw.flush();
				bw.write(GameSource.yourCard[1] + "\r\n");
				bw.flush();
				
				JOptionPane.showMessageDialog(null, ta.getText());
				judgeResult();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			ta.setText("상대방이 베팅금 입력중...");
		}
	}
	
	public void judgeResult() {
		mbm = GameSource.getScore(myCard);
		ybm = GameSource.getScore(yourCard);
		
		if(mbm > ybm) {
			JOptionPane.showMessageDialog(null, "이겼습니다!!");
			ta.setText("이겼습니다!!");
			nowMoney(bet_sum);
			whoFirst = false;
		} else if(mbm < ybm) {
			JOptionPane.showMessageDialog(null, "졌습니다...");
			ta.setText("졌습니다...");
			nowMoney(-bet_sum);
			whoFirst = true;
		} else {
			JOptionPane.showMessageDialog(null, "비겼습니다!");
			ta.setText("비겼습니다!");
			if(whoFirst) {
				whoFirst = false;
			}
			else {
				whoFirst = true;
			}
		}
		
		refresh();
		
	}

	// 베팅금액 변동
	public void updateMoney(int num) {
		// basic_money -= num;
		bet_sum += num;
		if(bet_sum <= 0) {
			JOptionPane.showMessageDialog(null, "파산하였습니다.");
		}
		
		bet_label.setText(bet_sum + ""); // 투자한 베팅 금액 표시
		// my_label.setText(basic_money + ""); // 삭감된 현재 금액 라벨에 표시
	}
	// 현재 금액 변동
		public void nowMoney(int num) {
			basic_money1 += num;
			
			my_label.setText(basic_money1 + "");
			
			if(basic_money1 <= 0) {
				JOptionPane.showMessageDialog(null, "파산하였습니다.");
				System.exit(0);
			}
		}

	public void refresh() {
		//bet_sum = 0;
		//bet_label.setText(bet_sum + "");
		receiveCnt = 0;
		otherCnt = 0;
		jb.setText("Start?");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) { // 종료버튼 클릭시 나가기
			try {
				s.close();
				System.exit(0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == jb) { // 스타트버튼 클릭시 실행
			jb.setText("Ready...");
			ta.setText(jb.getText()); // 상대방이 레디이면 게임시작 => 버튼도 베팅으로 표시되게한다.
			receiveCnt++; // 레디버튼 눌러서 게임시작준비
			
			//gs.shuffle(card); // 카드 섞기
			//
			if (whoFirst) {
				GameSource.shuffle(GameSource.card); // 카드섞기 (선인 사람이)
				GameSource.getCard(GameSource.myCard, GameSource.yourCard, GameSource.card); // 랜덤 카드 분배
				
				card = GameSource.card;
				myCard = GameSource.myCard;
				yourCard = GameSource.yourCard;
			}
		
		} 
		
		/*else if (e.getSource() == jb && receiveCnt == 1) { // 배팅버튼 클릭
			if (whoFirst) {
				// 선턴일때
				try {
					bet_amount2 = bet_amount1 = Integer.parseInt(JOptionPane.showInputDialog("베팅금액을 입력하시오"));
					updateMoney(bet_amount1);
					ta.append("\n" + bet_amount1 + "원을 베팅했습니다.");
					receiveCnt++; // 패 한번 받음
					mCard_label1.setIcon(ci.il[GameSource.myCard[0]]); // 패 이미지 삽입
					yCard_label1.setIcon(ci.il[GameSource.yourCard[0]]);
					

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "베팅금액 입력을 취소하였습니다.");
				}

			} else if (letYouGo) {
				ta.append("\n상대가 " + bet_amount1 + "원을 베팅하였습니다.");

				// 선턴인 상대가 베팅 완료시
				JOptionPane.showMessageDialog(null, "상대가 배팅금액을 " + bet_amount1 + "원으로 지정하였습니다.");
				updateMoney(bet_amount1);

				receiveCnt++;
				letYouGo = false; // 다시 상대의 베팅을 기다리기 위해
				mCard_label1.setIcon(ci.il[GameSource.myCard[0]]); // 패 이미지 삽입
				yCard_label1.setIcon(ci.il[GameSource.yourCard[0]]);

			} else {
				JOptionPane.showMessageDialog(null, "상대방이 입력하지 않았습니다.");
			}

		} else if (e.getSource() == jb && receiveCnt == 2) { // 패 한번 이상 받고 배팅버튼 클릭

			if (whoFirst) {

				
				 * if(otherCnt < 2) { JOptionPane.showMessageDialog(null, "상대방이 입력하지 않았습니다??.");
				 * return; }
				 
				bet_result = JOptionPane.showOptionDialog(null, "베팅옵션을 선택하세요!", "베팅옵션선택",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, bet_buttons, "콜(2배)");
				if (bet_result == 0) {
					// 따당인 경우
					ta.append("\n따당을 베팅하였습니다.");
					bet_amount1 *= 3; // 기본금에 3배
				} else if (bet_result == 1) {
					// 콜인 경우
					ta.append("\n콜을 베팅하였습니다.");
					bet_amount1 *= 2; // 기본금에 2배
				} else if (bet_result == 2) {
					ta.append("\n체크를 베팅하였습니다.");
				} else {
					ta.append("\n다이를 베팅하였습니다.");
				}
				receiveCnt++;

				// 따당, 콜, 체크인 경우 가격 변동
				if (bet_result <= 2) {
					updateMoney(bet_amount1);
				}
				mCard_label2.setIcon(ci.il[GameSource.myCard[1]]); // 패 이미지 삽입
				yCard_label2.setIcon(ci.il[GameSource.yourCard[1]]); // 패 이미지 삽입
				
				
			} else if (letYouGo) {
				// 선턴 배팅 완료시
				if (bet_result == 0) {
					ta.append("\n상대가 따당을 베팅하였습니다.");

					bet_result = JOptionPane.showOptionDialog(null, "베팅옵션을 선택하세요!", "베팅옵션선택",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, bet_button0, "다이");

					if (bet_result != 0)
						ta.append("\n다이를 베팅하였습니다.");
					else
						bet_amount1 *= 3;

				} else if (bet_result == 1) {
					ta.append("\n상대가 콜을 베팅하였습니다.");

					bet_result = JOptionPane.showOptionDialog(null, "베팅옵션을 선택하세요!", "베팅옵션선택",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, bet_button1, "다이");

					if (bet_result != 0)
						ta.append("\n다이를 베팅하였습니다.");
					else
						bet_amount1 *= 2;

				} else if (bet_result == 2) {
					ta.append("\n상대가 체크를 베팅하였습니다.");
					bet_result = JOptionPane.showOptionDialog(null, "베팅옵션을 선택하세요!", "베팅옵션선택",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, bet_button2, "다이");

					if (bet_result != 0)
						ta.append("\n다이를 베팅하였습니다.");

				} else {
					// 상대가 다이한 경우
					ta.append("\n상대가 다이를 베팅하였습니다.!!!?");
					System.out.println(bet_result);
				}
				// 따당, 콜, 체크인 경우 가격 변동
				if (bet_result == 0) {
					System.out.println(bet_amount1); // 0
					updateMoney(bet_amount1);
				}

				receiveCnt++;
				mCard_label2.setIcon(ci.il[GameSource.myCard[1]]); // 패 이미지 삽입
				yCard_label2.setIcon(ci.il[GameSource.yourCard[1]]); // 패 이미지 삽입
			} else {
				JOptionPane.showMessageDialog(null, "상대방이 베팅하지 않았습니다.");
				return; // 함수끝내기
			}

		}*/

	}

	/*
	 * public static void main(String[] args) { Board b = new Board();
	 * b.mainDisplay(); }
	 */

}
