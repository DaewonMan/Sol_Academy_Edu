package com.dwm.apr23_1_cd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class DAO {

	public String getKakaoBookData(HttpServletRequest req, HttpServletResponse res) {
		try {
			String q = req.getParameter("q");
			URL u = new URL("https://dapi.kakao.com/v2/search/book?query=" + q);
			HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
			huc.setRequestProperty("Authorization", "KakaoAK 1f9f7f82bfc4d9bd3bb9fc6d5d6efbdf");

			InputStream is = huc.getInputStream();

			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);

			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				sb.append(line); // 불러온거 stringbuffer에 붙인다.
			}
			//System.out.println(sb.toString());
			return sb.toString(); // 뭉쳐놓은거 리턴
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 네이버에 있는 데이터 꺼내다가 컨트롤러로 보내기
	public String getNaverMovieData(HttpServletRequest req, HttpServletResponse res) {
		try {
			String q = req.getParameter("q"); // 검색어를 받아서 처리
			URL u = new URL("https://openapi.naver.com/v1/search/movie.xml?query=" + q);

			HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();

			huc.addRequestProperty("X-Naver-Client-Id", "QHJ9Kaajxipg3xqFIrt6");
			huc.addRequestProperty("X-Naver-Client-Secret", "2MCQo3NDZT");

			InputStream is = huc.getInputStream();

			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);

			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				sb.append(line); // 불러온거 stringbuffer에 붙인다.
			}
			return sb.toString(); // 뭉쳐놓은거 리턴
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
