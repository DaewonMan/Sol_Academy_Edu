package com.dwm.apr16_1_pw.follow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwm.apr16_1_pw.member.Member;
import com.dwm.apr16_1_pw.member.MemberDAO;
import com.dwm.apr16_1_pw.post.Post;
import com.dwm.apr16_1_pw.post.PostDAO;

@Controller
public class FollowC {

	@Autowired private MemberDAO mDAO;
	@Autowired private PostDAO pDAO;
	@Autowired private FollowDAO fDAO;
	
	@RequestMapping(value = "/follower.cnt", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	public @ResponseBody Follows followerCnt(Follow f, HttpServletRequest req, HttpServletResponse res) {
		return fDAO.followerCnt(f, req, res);
	}
	
	@RequestMapping(value = "/follow.cnt", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	public @ResponseBody Follows followCnt(Follow f, HttpServletRequest req, HttpServletResponse res) {
		return fDAO.followCnt(f, req, res);
	}
	
	@RequestMapping(value = "/follow.ornot", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	public @ResponseBody Follows followOrNot(Follow f, HttpServletRequest req, HttpServletResponse res) {
		return fDAO.followOrNot(f, req, res);
	}
	
	@RequestMapping(value = "/delete.follower", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody void deleteFollower(Follow f, HttpServletRequest req, HttpServletResponse res) {
		fDAO.deleteFollower(f, req, res);
	}
	
	@RequestMapping(value = "/insert.follower", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody void insertFollower(Follow f, HttpServletRequest req, HttpServletResponse res) {
		fDAO.insertFollower(f, req, res);
	}
	
	@RequestMapping(value = "/follow.wave", method = RequestMethod.GET)
	public String wave(Member m, HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("contentArea", "home.jsp");
		req.setAttribute("memberTitle", "memberTitle.jsp");
		
		Member m1 = mDAO.MemberSearch(m, req, res);
		req.setAttribute("waveMember", m1);
		
		//
		/*Follow f = new Follow();
		f.setWf_id(m1.getWm_id());
		
		Member m2 = (Member) req.getSession().getAttribute("loginMember");
		f.setWf_follower(m2.getWm_id());
		
		Follows fs = fDAO.followOrNot(f, req, res);
		if(fs.getFollow().size() > 0) {
			req.setAttribute("followerOrNot", "ok");			
		} else {
			req.setAttribute("followerOrNot", "no");						
		}*/
		
		Post p = new Post();
		p.setWp_id(m1.getWm_id());
		pDAO.searchPost(p, req, res);
		
		return "member/loginOK";
		
	}
}
