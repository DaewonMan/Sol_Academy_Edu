package com.dwm.apr16_1_pw;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DateManager {
	
	public static void getCurrentYear(HttpServletRequest request, HttpServletResponse response) {
		Date now = new Date(); // 현재 시간 날짜
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		
		String curYear = sdf.format(now);
		
		request.setAttribute("curYear", curYear);
	}
}
