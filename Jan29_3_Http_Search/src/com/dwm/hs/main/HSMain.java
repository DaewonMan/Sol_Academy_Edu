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
			System.out.print("�˻��� : ");
			String str = sc.nextLine();
			// ���׳��ּҿ��� �ѱ�, Ư������ ������ �ȵ� => %2A�̷������� URL���ڵ����Ѽ� �ִ´�.
			str = URLEncoder.encode(str, "utf-8");

			String url = "https://openapi.naver.com/v1/search/news.xml";
			url += "?query=" + str; // �˻���

			URL u = new URL(url);
			huc = (HttpsURLConnection) u.openConnection();
			huc.addRequestProperty("X-Naver-Client-Id", "QHJ9Kaajxipg3xqFIrt6"); // ���̹��� ���ϴ� �߰����� ; �� Ŭ���̾�Ʈ ���̵�
			huc.addRequestProperty("X-Naver-Client-Secret", "2MCQo3NDZT"); // �� Ŭ���̾�Ʈ ���

			// ���� ���� �غ�
			InputStream is = huc.getInputStream();
			XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = xppf.newPullParser();
			xpp.setInput(is, "utf-8");

			int what = xpp.getEventType(); // ���� ��ġ�� �ִ°� ����; �����±� ����
			String tagName = null;
			// ���� ���� ������ �ݺ�
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

				xpp.next(); // ����ĭ���� �̵�
				what = xpp.getEventType(); // ���� ��ġ�� �ִ°� ����

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}

	}
}
