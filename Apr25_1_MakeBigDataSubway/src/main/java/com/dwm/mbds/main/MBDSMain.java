package com.dwm.mbds.main;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

// ����ö 18�� 3�� �Ѵް� ������ �����ϱ�
public class MBDSMain {
	public static void main(String[] args) {
		// ���Ͽ� utf-8 ���ڵ����� ������
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
					
					// �ּ�
					URL u = new URL("http://openapi.seoul.go.kr:8088/575a4655496b636839386f58586542/xml/CardSubwayStatsNew/1/600/2017"+ day);
					
					// ����
					HttpURLConnection huc = (HttpURLConnection) u.openConnection();
					
					// �ش�(x)
					
					// �ٿ� �޾�
					InputStream is = huc.getInputStream();
					
					// ���ڷ� �ٲ㼭 �ֿܼ� ���
					//System.out.println(MyConverter.convertToString(is));
					//System.out.println("==============================================");
					
					// ����
					XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
					// �ļ�
					XmlPullParser xpp = xppf.newPullParser();
					// �Ľ��� ���� �ֱ�
					xpp.setInput(is, "utf-8");
					
					// ���� ��ġ�� �ִ°� ����
					int type = xpp.getEventType(); // ���� ��ġ�� �ִ°� ���� <aaa> or ������ or </aaa>
					String tagName = null;
					boolean firstTag = false;
					String data = null;
					
					// �ݺ�
					while (type != XmlPullParser.END_DOCUMENT) {
						if (type == XmlPullParser.START_TAG) {
							// ���� ��ġ�� �ִ°� < > ��
							// 1. �±׸� �ֿܼ� ���
							// System.out.println(xpp.getName());
							
							tagName = xpp.getName(); // ���� �±� ����
							// ���� �±װ� USE_DT�̸�
							if (tagName.equals("row")) { // �ܼ���
								firstTag = true;
								data = "";
							}
							
						} else if (type == XmlPullParser.TEXT) {
							// ���� ��ġ�� �ִ°� ���ڸ�
							// 2. ���� �ֿܼ� ���
							// ������ ��
							// System.out.println(xpp.getText());
							
							if (firstTag && (tagName.equals("USE_DT") || tagName.equals("LINE_NUM")
									|| tagName.equals("SUB_STA_NM") || tagName.equals("RIDE_PASGR_NUM"))) { // �Ӽ���
								data += xpp.getText() + ",";
								// System.out.println(xpp.getText());
							} else if (firstTag && tagName.equals("ALIGHT_PASGR_NUM")) {
								data += xpp.getText();
							}
							
						} else if (type == XmlPullParser.END_TAG) {
							// ���� ��ġ�� �ִ°� </ > ��
							// 3. �±׸� �ֿܼ� ���
							// �ݴ� �±��� ��
							// System.out.println(xpp.getName());
							if (firstTag && (xpp.getName().equals("USE_DT") || xpp.getName().equals("LINE_NUM")
									|| xpp.getName().equals("SUB_STA_NM") || xpp.getName().equals("RIDE_PASGR_NUM")
									|| xpp.getName().equals("ALIGHT_PASGR_NUM"))) { // �ܼ���
								tagName = ""; // ������
							} else if (xpp.getName().equals("row")) { // �ܼ���
								firstTag = false;
								// data = data.replace("\r\n", "");
								// System.out.println(data);
								bw.write(data + "\r\n");
								bw.flush();
							}
							
						}
						// ���� �ɷ�
						type = xpp.next(); // ��������; �׸��� �װ� ���� �̴� ��ɱ���
						// type = xpp.getEventType();
					}
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
