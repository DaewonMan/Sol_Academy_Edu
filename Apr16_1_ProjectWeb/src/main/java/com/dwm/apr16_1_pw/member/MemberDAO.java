package com.dwm.apr16_1_pw.member;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class MemberDAO {
	@Autowired
	private SqlSession ss;
	
	public void updateMemberOpen(Member m, HttpServletRequest req, HttpServletResponse res) {
			try {
				if(ss.getMapper(MemberMapper.class).updateMemberOpen(m) == 1) {
					res.getWriter().print("OK");
				} else {
					res.getWriter().print("FAIL");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public Members getMemberByIdnPw(Member m, HttpServletRequest req, HttpServletResponse res) {
		return new Members(ss.getMapper(MemberMapper.class).getMemberByIdnPw(m));
	}
	public Member MemberSearch(Member m, HttpServletRequest req, HttpServletResponse res) {
		return ss.getMapper(MemberMapper.class).getMemberById(m);
	}
	
	public Members idSearch(Member m, HttpServletRequest req, HttpServletResponse res) {
		return new Members(ss.getMapper(MemberMapper.class).getMemberById3(m));
	}
	
	public Members idCheck(Member m, HttpServletRequest req, HttpServletResponse res) {
		return new Members(ss.getMapper(MemberMapper.class).getMemberById2(m));
	}
	public void deleteMember(HttpServletRequest req, HttpServletResponse res) {
		try {
			// 현재 로그인된 사람 정보
			Member im = (Member) req.getSession().getAttribute("loginMember");
			
			if (ss.getMapper(MemberMapper.class).deleteMember(im) == 1) {
				req.setAttribute("r", "회원정보 삭제 성공");
				
				String path = req.getSession().getServletContext().getRealPath("resources/img");
				
				String oldFile = im.getWm_img(); // 원래 있던 사진
				
				oldFile = URLDecoder.decode(oldFile, "euc-kr");
				File f = new File(path + "/" + oldFile);
				f.delete();
			} else {
				req.setAttribute("r", "회원정보 삭제 실패");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "DB문제");
		}
	}
	public void updateMember(Member m, HttpServletRequest req, HttpServletResponse res) {
		try {
			String path = req.getSession().getServletContext().getRealPath("resources/img");
			System.out.println(path);
			MultipartRequest mr = new MultipartRequest(req, path, 30 * 1024 * 1024, "euc-kr",
					new DefaultFileRenamePolicy());
			
			// 현재 로그인된 사람 정보
			Member im = (Member) req.getSession().getAttribute("loginMember");
			String oldFile = im.getWm_img(); // 원래 있던 사진
			
			String wm_img = mr.getFilesystemName("wm_img");
			if(wm_img != null) {
				wm_img = URLEncoder.encode(wm_img, "euc-kr");
				wm_img = wm_img.replace("+", " ");				
				
				oldFile = URLDecoder.decode(oldFile, "euc-kr");
				File f = new File(path + "/" + oldFile);
				f.delete();
			} else {
				wm_img = im.getWm_img();
			}

			String wm_pw = mr.getParameter("wm_pw");
			String wm_name = mr.getParameter("wm_name");
			String wm_add1 = mr.getParameter("wm_add1");
			String wm_add2 = mr.getParameter("wm_add2");
			String wm_add3 = mr.getParameter("wm_add3");

			int y = Integer.parseInt(mr.getParameter("wm_y"));
			int m1 = Integer.parseInt(mr.getParameter("wm_m"));
			int d = Integer.parseInt(mr.getParameter("wm_d"));

			String wm_birth = String.format("%s%02d%02d", y, m1, d);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			m.setWm_id(im.getWm_id());
			m.setWm_pw(wm_pw);
			m.setWm_name(wm_name);
			m.setWm_birth(sdf.parse(wm_birth));
			m.setWm_add1(wm_add1);
			m.setWm_add2(wm_add2);
			m.setWm_add3(wm_add3);
			m.setWm_img(wm_img);

			if (ss.getMapper(MemberMapper.class).updateMember(m) == 1) {
				req.setAttribute("r", "회원정보 수정 성공");
				req.getSession().setAttribute("loginMember", ss.getMapper(MemberMapper.class).getMemberById(m));
			} else {
				req.setAttribute("r", "회원정보 수정 실패");

			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "DB문제");
		}
	}
	
	public void autologin(HttpServletRequest req, HttpServletResponse res) {
		Cookie[] cookies = req.getCookies();

		// 쿠키 있을 때만 ; 없는데 뭐하면 난리남
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("wonstaAutoLoginID")) {
					String wm_id = c.getValue();
					
					// 로그아웃 안누르고 집에 갔었으면
					if(wm_id != null) {
						Member m = new Member();
						m.setWm_id(wm_id);
						
						Member dbM = ss.getMapper(MemberMapper.class).getMemberById(m);
						if(dbM != null) {
							req.getSession().setAttribute("loginMember", dbM);
							req.getSession().setMaxInactiveInterval(1200);
						}
					}
				}
			}
		}
	}

	public boolean loginCheck(HttpServletRequest req, HttpServletResponse res) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		if (m != null) {
			return true;
		} else {
			return false;
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse res) {
		req.getSession().setAttribute("loginMember", null);

		// 이 컴퓨터에 만들어져있는 모든 쿠키들
		Cookie[] cookies = req.getCookies();

		// 쿠키 있을 때만 ; 없는데 뭐하면 난리남
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("wonstaAutoLoginID")) {
					c.setValue(null); // id값 삭제
					res.addCookie(c); // 실행
				}
			}
		}
	}

	public void login(Member m, HttpServletRequest req, HttpServletResponse res) {
		try {
			Member dbM = ss.getMapper(MemberMapper.class).getMemberById(m);

			if (dbM != null) {
				if (m.getWm_pw().equals(dbM.getWm_pw())) {
					// req.setAttribute("r", "성공");
					req.getSession().setAttribute("loginMember", dbM);
					req.getSession().setMaxInactiveInterval(1200);

					if (req.getParameter("wm_autologin") != null) {
						Cookie c = new Cookie("wonstaAutoLoginID", dbM.getWm_id());
						c.setMaxAge(86400); // 하루동안 자동로그인
						res.addCookie(c);
					}
				} else {
					req.setAttribute("r", "비번 오류");
				}
			} else {
				req.setAttribute("r", "그런 계정 없음");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "DB 서버문제");
		}
	}

	public void join(Member m, HttpServletRequest req, HttpServletResponse res) {
		try {

			String path = req.getSession().getServletContext().getRealPath("resources/img");
			System.out.println(path);
			MultipartRequest mr = new MultipartRequest(req, path, 30 * 1024 * 1024, "euc-kr",
					new DefaultFileRenamePolicy());

			String wm_id = mr.getParameter("wm_id");
			String wm_pw = mr.getParameter("wm_pw");
			String wm_name = mr.getParameter("wm_name");
			String wm_add1 = mr.getParameter("wm_add1");
			String wm_add2 = mr.getParameter("wm_add2");
			String wm_add3 = mr.getParameter("wm_add3");

			int y = Integer.parseInt(mr.getParameter("wm_y"));
			int m1 = Integer.parseInt(mr.getParameter("wm_m"));
			int d = Integer.parseInt(mr.getParameter("wm_d"));

			String wm_birth = String.format("%s%02d%02d", y, m1, d);

			String wm_img = mr.getFilesystemName("wm_img");
			wm_img = URLEncoder.encode(wm_img, "euc-kr");
			wm_img = wm_img.replace("+", " ");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			m.setWm_id(wm_id);
			m.setWm_pw(wm_pw);
			m.setWm_name(wm_name);
			m.setWm_birth(sdf.parse(wm_birth));
			m.setWm_add1(wm_add1);
			m.setWm_add2(wm_add2);
			m.setWm_add3(wm_add3);
			m.setWm_img(wm_img);
			m.setWm_open("open"); // 전체공개
			
			if (ss.getMapper(MemberMapper.class).join(m) == 1) {
				req.setAttribute("r", "가입 성공");
			} else {
				req.setAttribute("r", "가입 실패");

			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "DB 문제");
		}
	}
}
