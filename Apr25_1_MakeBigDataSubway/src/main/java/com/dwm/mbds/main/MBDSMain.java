package com.dwm.mbds.main;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

// 지하철 18년 3월 한달간 데이터 저장하기
public class MBDSMain {
	public static void main(String[] args) {
		// 파일에 utf-8 인코딩으로 적혀야
		FileOutputStream fos = null;
		try {
			// CSV(Comma Separated Value)
			fos = new FileOutputStream("C:\\Edu18\\Bigdata\\subway.csv", true);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			BufferedWriter bw = new BufferedWriter(osw);

			String day = null;
			//int days = 0;
			for(int i = 1;i <= 12 ;i++) {
				/*if(i == 4 || i == 6 || i == 9 || i == 11 ) {
					days = 30;
				} else if (i == 2) {
					days = 28;
				} else {
					days = 31;
				}*/
				for (int j = 1; j <= 31; j++) {
					day = String.format("%02d%02d", i, j);
					
					// 주소
					URL u = new URL("http://openapi.seoul.go.kr:8088/575a4655496b636839386f58586542/xml/CardSubwayStatsNew/1/600/2017"+ day);
					
					// 연결
					HttpURLConnection huc = (HttpURLConnection) u.openConnection();
					
					// 해더(x)
					
					// 다운 받아
					InputStream is = huc.getInputStream();
					
					// 글자로 바꿔서 콘솔에 출력
					//System.out.println(MyConverter.convertToString(is));
					//System.out.println("==============================================");
					
					// 공장
					XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
					// 파서
					XmlPullParser xpp = xppf.newPullParser();
					// 파싱할 내용 주기
					xpp.setInput(is, "utf-8");
					
					// 현재 위치에 있는게 뭔지
					int type = xpp.getEventType(); // 현재 위치에 있는게 뭐냐 <aaa> or ㅋㅋㅋ or </aaa>
					String tagName = null;
					boolean firstTag = false;
					String data = null;
					
					// 반복
					while (type != XmlPullParser.END_DOCUMENT) {
						if (type == XmlPullParser.START_TAG) {
							// 현재 위치에 있는게 < > 면
							// 1. 태그명 콘솔에 출력
							// System.out.println(xpp.getName());
							
							tagName = xpp.getName(); // 시작 태그 저장
							// 시작 태그가 USE_DT이면
							if (tagName.equals("row")) { // 단수형
								firstTag = true;
								data = "";
							}
							
						} else if (type == XmlPullParser.TEXT) {
							// 현재 위치에 있는게 글자면
							// 2. 내용 콘솔에 출력
							// 글자일 때
							// System.out.println(xpp.getText());
							
							if (firstTag && (tagName.equals("USE_DT") || tagName.equals("LINE_NUM")
									|| tagName.equals("SUB_STA_NM") || tagName.equals("RIDE_PASGR_NUM"))) { // 속성명
								data += xpp.getText() + ",";
								// System.out.println(xpp.getText());
							} else if (firstTag && tagName.equals("ALIGHT_PASGR_NUM")) {
								data += xpp.getText();
							}
							
						} else if (type == XmlPullParser.END_TAG) {
							// 현재 위치에 있는게 </ > 면
							// 3. 태그면 콘솔에 출력
							// 닫는 태그일 때
							// System.out.println(xpp.getName());
							if (firstTag && (xpp.getName().equals("USE_DT") || xpp.getName().equals("LINE_NUM")
									|| xpp.getName().equals("SUB_STA_NM") || xpp.getName().equals("RIDE_PASGR_NUM")
									|| xpp.getName().equals("ALIGHT_PASGR_NUM"))) { // 단수형
								tagName = ""; // 날리기
							} else if (xpp.getName().equals("row")) { // 단수형
								firstTag = false;
								// data = data.replace("\r\n", "");
								// System.out.println(data);
								bw.write(data + "\r\n");
								bw.flush();
							}
							
						}
						// 다음 걸로
						type = xpp.next(); // 다음으로; 그리고 그게 뭔지 뽑는 기능까지
						// type = xpp.getEventType();
					}
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
