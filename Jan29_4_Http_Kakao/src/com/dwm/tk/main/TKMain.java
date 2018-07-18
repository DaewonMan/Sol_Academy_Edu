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

//����Ƽ�� �� Ű
//4c51f27d4f50782a834f52fbf9badf27
//REST API Ű
//1f9f7f82bfc4d9bd3bb9fc6d5d6efbdf
//JavaScript Ű
//e50d5b2ca7b9de9bc0ee7e6b894452ae
//Admin Ű
//de189031acb33bd006f33c2a09aa4e24
public class TKMain {
	public static void main(String[] args) {
		Scanner sc = null;
		HttpsURLConnection huc = null;
		try {
			sc = new Scanner(System.in);
			System.out.print("�˻��� : ");
			String str = sc.next();

			str = URLEncoder.encode(str, "utf-8"); // ���ͳ� �ּҿ� �� �ѱ�ó��

			String url = "https://dapi.kakao.com/v2/search/book"; // å�˻� �ּ�
			url += "?query=" + str; // �˻���

			URL u = new URL(url);
			huc = (HttpsURLConnection) u.openConnection();
			huc.addRequestProperty("Authorization", "KakaoAK de189031acb33bd006f33c2a09aa4e24");

			
			// ���� ���� �غ�
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			/*BufferedReader br = new BufferedReader(isr);
			
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				
			}*/
			// javascript������ {}�� ��ü�̴�.
			JSONParser jp = new JSONParser();
			// ���� ���� [�� JsonArray
			// {�� JsonObject
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
