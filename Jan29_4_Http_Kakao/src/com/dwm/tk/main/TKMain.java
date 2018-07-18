package com.dwm.tk.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//네이티브 앱 키
//4c51f27d4f50782a834f52fbf9badf27
//REST API 키
//1f9f7f82bfc4d9bd3bb9fc6d5d6efbdf
//JavaScript 키
//e50d5b2ca7b9de9bc0ee7e6b894452ae
//Admin 키
//de189031acb33bd006f33c2a09aa4e24
public class TKMain {
	public static void main(String[] args) {
		Scanner sc = null;
		HttpsURLConnection huc = null;
		try {
			sc = new Scanner(System.in);
			System.out.print("검색어 : ");
			String str = sc.next();

			str = URLEncoder.encode(str, "utf-8"); // 인터넷 주소에 들어갈 한글처리

			String url = "https://dapi.kakao.com/v2/search/book"; // 책검색 주소
			url += "?query=" + str; // 검색어

			URL u = new URL(url);
			huc = (HttpsURLConnection) u.openConnection();
			huc.addRequestProperty("Authorization", "KakaoAK de189031acb33bd006f33c2a09aa4e24");

			
			// 내용 빼낼 준비
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			/*BufferedReader br = new BufferedReader(isr);
			
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				
			}*/
			// javascript에서는 {}가 객체이다.
			JSONParser jp = new JSONParser();
			// 제일 앞이 [면 JsonArray
			// {면 JsonObject
			JSONObject kakaoData = (JSONObject) jp.parse(isr);
			
			JSONArray documents = (JSONArray) kakaoData.get("documents");
			JSONObject book = null;
			for (int i = 0; i < documents.size(); i++) {
				book = (JSONObject) documents.get(i);
				System.out.println(book.get("title"));
				System.out.println(book.get("sale_price"));
				System.out.println("---------------------");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			sc.close();
		}
	}
}
