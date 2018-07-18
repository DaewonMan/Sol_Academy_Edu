package com.dwm.HW.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.dwm.HW.coordinate.Area;

public class HWMain {
	public static void main(String[] args) {
		Scanner sc = null;
		// 동네
		// 지명, x좌표, y좌표
		Area a1 = new Area("경기도 고양시 덕양구 행신3동", 57, 128);
		Area a2 = new Area("경기도 수원시", 61, 120);
		Area a3 = new Area("대구광역시 북구", 88, 92);
		Area a4 = new Area("제주특별차지도 서귀포시", 56, 33);
		// a.print();
		Area[] as = { a1, a2, a3, a4 };
		for (int i = 0; i < as.length; i++) {
			System.out.printf("%d) %s\n", i + 1, as[i].getName());
		}

		System.out.print("뭐 : ");

		HttpURLConnection huc = null;
		try {
			sc = new Scanner(System.in);
			int num = sc.nextInt();
			int x = as[num - 1].getX();
			int y = as[num - 1].getY();
			// System.out.println(as[num-1].getX());
			// System.out.println(as[num-1].getY());

			String url = String.format("http://www.weather.go.kr/wid/queryDFS.jsp?gridx=%d&gridy=%d", x, y);

			URL u = new URL(url);
			huc = (HttpURLConnection) u.openConnection();

			// 내용 빼낼 준비
			InputStream is = huc.getInputStream();
			/* 1 */
			// InputStreamReader isr = new InputStreamReader(is, "utf-8");
			// BufferedReader br = new BufferedReader(isr);
			//
			// String line = null;
			// while ((line = br.readLine()) != null) {
			// System.out.println(line);
			// }

			/* 2 */
			// XML or JSON에서 필요한것만 빼내는 등의 가공행위
			// parsing
			XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = xppf.newPullParser();
			xpp.setInput(is, "utf-8");
			
			String tagName = null;
			int what = xpp.getEventType(); // 현재 위치에 있는게 뭐냐; 시작태그 저장
			// 문서 끝날 때까지 반복
			while (what != XmlPullParser.END_DOCUMENT) {
				if (what == XmlPullParser.START_TAG) {
					tagName = xpp.getName();
					
				} else if(what == XmlPullParser.TEXT) {
					if(tagName.equals("hour")) {
						System.out.printf("~%s시 : ",xpp.getText());
					}
					else if (tagName.equals("temp")) {
						System.out.printf("%s , ", xpp.getText());
					}
					else if (tagName.equals("wfKor")) {
						System.out.println(xpp.getText());
					}
				} else if(what == XmlPullParser.END_TAG) {
					//System.out.println(xpp.getName());
					tagName = "";
				}
				
				xpp.next(); // 다음칸으로 이동
				what = xpp.getEventType(); // 현재 위치에 있는게 뭐냐
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			huc.disconnect();
		}

	}

}
