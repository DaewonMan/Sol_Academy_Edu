package com.dwm.apr16_1_pw;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dwm.apr16_1_pw.member.Member;
import com.dwm.apr16_1_pw.member.MemberDAO;
import com.dwm.apr16_1_pw.post.Post;
import com.dwm.apr16_1_pw.post.PostDAO;

@Controller
public class HomeController {
	
	@Autowired private MemberDAO mDAO;
	@Autowired private PostDAO pDAO;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req, HttpServletResponse res) {
		
		req.setAttribute("contentArea", "home.jsp");
		req.setAttribute("memberTitle", "loginOkTitle.jsp");
		mDAO.autologin(req, res);
		if(mDAO.loginCheck(req, res)) {
			Member m = (Member) req.getSession().getAttribute("loginMember");
			req.setAttribute("waveMember", m);
			
			Post p = new Post();
			p.setWp_id(m.getWm_id());
			pDAO.searchPost(p, req, res);
			
			return "member/loginOK";
		} else {
			req.setAttribute("loginArea", "member/login.jsp");
		}
		return "index";
	}
	@RequestMapping(value = "/home.go", method = RequestMethod.GET)
	public String homeGo(HttpServletRequest req, HttpServletResponse res) {
		
		req.setAttribute("contentArea", "home.jsp");
		req.setAttribute("memberTitle", "loginOkTitle.jsp");
		mDAO.autologin(req, res);
		if(mDAO.loginCheck(req, res)) {
			Member m = (Member) req.getSession().getAttribute("loginMember");
			req.setAttribute("waveMember", m);
			//req.setAttribute("followerOrNot", "ok");
			Post p = new Post();
			p.setWp_id(m.getWm_id());
			pDAO.searchPost(p, req, res); // 게시물 가져오는 메소드
			
			return "member/loginOK";
		} else {
			req.setAttribute("loginArea", "member/login.jsp");
		}
		return "index";
	}
}
