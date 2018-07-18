package com.dwm.hc.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

// 웹 크롤링
public class HCMain {
	public static void main(String[] args) {
		DefaultHttpClient dhc = null; // 구버전?이라서
		try {
			dhc = new DefaultHttpClient();
			// 요청(네이버에 데이터 요청)
			HttpGet hg = new HttpGet("http://www.naver.com");
			
			// 응답(이코드를 반복문 돌리면 ddos)
			HttpResponse hr = dhc.execute(hg);
			
			// 응답내용
			HttpEntity he = hr.getEntity();
			
			// 응답 내용에서 데이터 빼낼 준비
			InputStream is  = he.getContent();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		
	}
}
