package com.dwm.hc.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

// �� ũ�Ѹ�
public class HCMain {
	public static void main(String[] args) {
		DefaultHttpClient dhc = null; // ������?�̶�
		try {
			dhc = new DefaultHttpClient();
			// ��û(���̹��� ������ ��û)
			HttpGet hg = new HttpGet("http://www.naver.com");
			
			// ����(���ڵ带 �ݺ��� ������ ddos)
			HttpResponse hr = dhc.execute(hg);
			
			// ���䳻��
			HttpEntity he = hr.getEntity();
			
			// ���� ���뿡�� ������ ���� �غ�
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
