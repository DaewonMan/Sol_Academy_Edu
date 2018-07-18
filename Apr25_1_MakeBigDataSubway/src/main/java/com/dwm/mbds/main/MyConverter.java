package com.dwm.mbds.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyConverter {
	// inputStream을 넣으면 글자로 바꿔줄 메소드
	public static String convertToString(InputStream is) throws IOException {
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer();
		String line = null;
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		
		return sb.toString();
	}
}
