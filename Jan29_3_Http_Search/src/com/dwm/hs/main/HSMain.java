package com.dwm.hs.main;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

// QHJ9Kaajxipg3xqFIrt6
// 2MCQo3NDZT
public class HSMain {
	public static void main(String[] args) {
		HttpsURLConnection huc = null;
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			System.out.print("검색어 : ");
			String str = sc.nextLine();
			// 인테넷주소에는 한글, 특수문자 넣으면 안됨 => %2A이런식으로 URL인코딩시켜서 넣는다.
			str = URLEncoder.encode(str, "utf-8");

			String url = "https://openapi.naver.com/v1/search/news.xml";
			url += "?query=" + str; // 검색어

			URL u = new URL(url);
			huc = (HttpsURLConnection) u.openConnection();
			huc.addRequestProperty("X-Naver-Client-Id", "QHJ9Kaajxipg3xqFIrt6"); // 네이버가 원하는 추가정보 ; 내 클라이언트 아이디
			huc.addRequestProperty("X-Naver-Client-Secret", "2MCQo3NDZT"); // 내 클라이언트 비번

			// 내용 빼낼 준비
			InputStream is = huc.getInputStream();
			XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = xppf.newPullParser();
			xpp.setInput(is, "utf-8");

			int what = xpp.getEventType(); // 현재 위치에 있는게 뭐냐; 시작태그 저장
			String tagName = null;
			// 문서 끝날 때까지 반복
			while (what != XmlPullParser.END_DOCUMENT) {
				if (what == XmlPullParser.START_TAG) {
					tagName = xpp.getName();
				} else if (what == XmlPullParser.TEXT) {
					if (tagName.equals("title")) {
						System.out.printf("%s\n", xpp.getText());
					} else if (tagName.equals("description")){
						System.out.println(xpp.getText());
					} else if (tagName.equals("pubDate")) {
						System.out.println(xpp.getText());
						System.out.println();
					} 
					
				} else if (what == XmlPullParser.END_TAG) {
					tagName = "";
				}

				xpp.next(); // 다음칸으로 이동
				what = xpp.getEventType(); // 현재 위치에 있는게 뭐냐

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}

	}
}
