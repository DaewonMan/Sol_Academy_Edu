package com.dwm.apr16_1_pw.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwm.apr16_1_pw.DateManager;

@Controller
public class MemberC {
	@Autowired private MemberDAO mDAO;
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginDo(HttpServletRequest req, HttpServletResponse res) {
		return "member/login";
	}
	@RequestMapping(value = "/login.go", method = RequestMethod.POST)
	public String loginGo(Member m, HttpServletRequest req, HttpServletResponse res) {
		
		mDAO.login(m, req, res);
		mDAO.autologin(req, res);
		if(mDAO.loginCheck(req, res)) {
			return "member/goToIndex";
		}
		
		return "index";
	}
	/*@RequestMapping(value = "/join.go", method = RequestMethod.GET)
	public String joinGo(HttpServletRequest req, HttpServletResponse res) {
		DateManager.getCurrentYear(req, res);
		return "member/join";
	}*/
	@RequestMapping(value = "/join.go", method = RequestMethod.GET)
	public String joinGo(HttpServletRequest req, HttpServletResponse res) {
		DateManager.getCurrentYear(req, res);
		req.setAttribute("loginArea", "member/join.jsp");
		return "index";
	}
	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public String welcome(Member m, HttpServletRequest req, HttpServletResponse res) {
		mDAO.join(m, req, res);
		return "member/goToIndex";
	}
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logoutDo(Member m, HttpServletRequest req, HttpServletResponse res) {
		
		mDAO.logout(req, res);
		req.setAttribute("loginArea", "member/login.jsp");
		req.setAttribute("contentArea", "home.jsp");
		
		return "member/goToIndex";
	}
	@RequestMapping(value = "/update.member.do", method = RequestMethod.GET)
	public String memberUpdateDo(Member m, HttpServletRequest req, HttpServletResponse res) {
		if(mDAO.loginCheck(req, res)) {
			req.setAttribute("waveMember", req.getSession().getAttribute("loginMember"));
			DateManager.getCurrentYear(req, res);
			return "member/update";
		}
		
		return "member/goToIndex";
	}
	@RequestMapping(value = "/update.member.go", method = RequestMethod.POST)
	public String memberUpdateGo(Member m, HttpServletRequest req, HttpServletResponse res) {
		if(mDAO.loginCheck(req, res)) {
			mDAO.updateMember(m, req, res);
		}
		
		return "member/goToIndex";
	}
	@RequestMapping(value = "/delete.member.go", method = RequestMethod.GET)
	public String memberDeleteGo(HttpServletRequest req, HttpServletResponse res) {
		if(mDAO.loginCheck(req, res)) {
			mDAO.deleteMember(req, res);
			mDAO.logout(req, res);
		}
		
		return "member/goToIndex";
	}

	@RequestMapping(value = "/member.id.check", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	public @ResponseBody Members idCheck(Member m, HttpServletRequest req, HttpServletResponse res) {
		return mDAO.idCheck(m, req, res);
	}
	@RequestMapping(value = "/member.id.search", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	public @ResponseBody Members idSearch(Member m, HttpServletRequest req, HttpServletResponse res) {
		return mDAO.idSearch(m, req, res);
	}
	@RequestMapping(value = "/member.info.check", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	public @ResponseBody Members memberInfoCheck(Member m, HttpServletRequest req, HttpServletResponse res) {
		return mDAO.getMemberByIdnPw(m, req, res);
	}
	@RequestMapping(value = "/update.member.open", method = RequestMethod.GET)
	public void memberOpen(Member m, HttpServletRequest req, HttpServletResponse res) {
		mDAO.updateMemberOpen(m, req, res);
	}
}
