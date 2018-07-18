package com.dwn.hl.main;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HLMain {
	public static void main(String[] args) {
		Scanner sc = null;
		HttpsURLConnection huc = null;
		try {
			sc = new Scanner(System.in);
			System.out.print("동네명 : ");
			String area = sc.next();
			System.out.print("검색어 : ");
			String search = sc.next();
			
			// 인터넷 주소 값을 넣기 위해서 인코딩
			area = URLEncoder.encode(area, "utf-8");
			search = URLEncoder.encode(search, "utf-8");
			
			String url = "https://dapi.kakao.com/v2/local/search/address.json";
			url += "?query=" + area;
			
			URL u = new URL(url);
			huc = (HttpsURLConnection) u.openConnection();
			huc.addRequestProperty("Authorization", "KakaoAK de189031acb33bd006f33c2a09aa4e24");
			
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			/*BufferedReader br = new BufferedReader(isr);
			
			String line = null;
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}*/
			JSONParser jp = new JSONParser();
			JSONObject kakaoData = (JSONObject) jp.parse(isr);
			JSONArray ja = (JSONArray) kakaoData.get("documents");
			JSONObject datas = null;
			JSONObject document = null;
			
			String x = null;
			String y = null;
			for (int i = 0; i < ja.size(); i++) {
				datas = (JSONObject) ja.get(i);
				System.out.println(datas.get("address_name"));
				
				x = (String) datas.get("x");
				y = (String) datas.get("y");
				
				// 실제 검색
				url = "https://dapi.kakao.com/v2/local/search/keyword.json";
				url += "?query=" + search + "&x=" + x + "&y=" + y + "&radius=10000";
				
				u = new URL(url);
				huc = (HttpsURLConnection) u.openConnection();
				huc.addRequestProperty("Authorization", "KakaoAK de189031acb33bd006f33c2a09aa4e24");
				
				is = huc.getInputStream();
				isr = new InputStreamReader(is, "utf-8");
				
				//jp = new JSONParser();
				kakaoData = (JSONObject) jp.parse(isr);
				ja = (JSONArray) kakaoData.get("documents");
				//document = null;
				
				for (int j = 0; j < ja.size(); j++) {
					document = (JSONObject) ja.get(j);
					System.out.println(document.get("place_name"));
					System.out.println(document.get("address_name"));
					System.out.println(document.get("phone"));
					System.out.println("==========================");
				}
				
				
				/*BufferedReader br = new BufferedReader(isr);
				
				String line = null;
				while((line = br.readLine()) != null) {
					System.out.println(line);
				}
				*/
				System.out.println("-----------------------");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
