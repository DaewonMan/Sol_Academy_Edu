package com.dwm.apr16_1_pw.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwm.apr16_1_pw.member.Member;
import com.dwm.apr16_1_pw.member.MemberDAO;

@Controller
public class PostC {
	@Autowired private PostDAO pDAO;
	@Autowired private MemberDAO mDAO;
	
	@RequestMapping(value = "/post.go", method = RequestMethod.GET)
	public String postGo(HttpServletRequest req, HttpServletResponse res) {
		if(mDAO.loginCheck(req, res)) {
			req.setAttribute("waveMember", req.getSession().getAttribute("loginMember"));
			req.setAttribute("contentArea", "post/post.jsp");
			req.setAttribute("memberTitle", "loginOkTitle.jsp");
			return "member/loginOK";
		}
		
		return "member/goToIndex";
	}
	@RequestMapping(value = "/post.do", method = RequestMethod.POST)
	public String postDo(Post p, HttpServletRequest req, HttpServletResponse res) {
		if(mDAO.loginCheck(req, res)) {
			Member m = (Member) req.getSession().getAttribute("loginMember");
			pDAO.addPost(m, p, req, res);
		}
		
		return "member/goToIndex";
	}
	
	// 게시물 보기
	@RequestMapping(value = "/post.show", method = RequestMethod.GET)
	public String postShow(Post p, HttpServletRequest req, HttpServletResponse res) {
		//req.setAttribute("loginArea", "member/login.jsp");
		req.setAttribute("contentArea", "home.jsp");
		
		Member lm = (Member) req.getSession().getAttribute("loginMember");
		if(p.getWp_id().equals(lm.getWm_id())) {
			req.setAttribute("memberTitle", "loginOkTitle.jsp");			
		} else {
			req.setAttribute("memberTitle", "memberTitle.jsp");						
		}
		
		if(mDAO.loginCheck(req, res)) {
			pDAO.searchPostByNo(p, req, res); //게시물 눌렀을 때 해당 게시물의 정보
			
			Reple r = new Reple();
			r.setWr_rno(p.getWp_no());
			pDAO.searchRepleByRNo(r, req, res); // 댓글 리스트
			
			//return "post/postDetail";
			Member m = new Member();
			m.setWm_id(p.getWp_id());
			req.setAttribute("waveMember", mDAO.MemberSearch(m, req, res)); // 게시물 주인의 정보
			
			pDAO.searchPost(p, req, res); // 각 회원의 게시물들 목록
			req.setAttribute("postDetail", "post/postDetail.jsp");
			return "member/loginOK";
			
		}
		return "member/goToIndex";
	}
	@RequestMapping(value = "/post.reple.do", method = RequestMethod.GET)
	public String repleDo(Reple r, HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("contentArea", "home.jsp");
		
		if(mDAO.loginCheck(req, res)) {
			pDAO.insertReple(r, req, res); // 댓글 추가
			
			//Reple r1 = pDAO.searchRepleByRNo2(r, req, res); // 해당 댓글의 정보; 이게 문제인듯
			
			Post p1 = new Post();
			p1.setWp_no(r.getWr_rno());
			Post p2 = pDAO.searchPostByNo3(p1, req, res); // 게시물 주인의 정보
			
			Member m = (Member) req.getSession().getAttribute("loginMember");
			
			// 게시물 주인과 댓글 단 사람 일치 여부
			if(p2.getWp_id().equals(m.getWm_id())) {
				req.setAttribute("memberTitle", "loginOkTitle.jsp");			
			} else {
				req.setAttribute("memberTitle", "memberTitle.jsp");			
			}
			
			//Post p = new Post();
			//p.setWp_no(r.getWr_rno());
			pDAO.searchPostByNo(p2, req, res); // 게시물에 출력될 게시물 주인의 정보
			
			pDAO.searchRepleByRNo(r, req, res); // 해당 게시물에서 댓글 단 사람 정보
			
			
			//p.setWp_id(m.getWm_id()); // 수정필요
			pDAO.searchPost(p2, req, res); // 모든 게시물; 수정필요
			
			Member wm = new Member();
			wm.setWm_id(p2.getWp_id());
			req.setAttribute("waveMember", mDAO.MemberSearch(wm, req, res)); // 댓글 단 사람 정보; 수정필요
			
			req.setAttribute("postDetail", "post/postDetail.jsp");
			return "member/loginOK";
			
			//return "post/postDetail";
		}
		return "member/goToIndex";
	}
	
	@RequestMapping(value = "/post.count", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	public @ResponseBody Posts postCount(Post p,HttpServletRequest req, HttpServletResponse res) {
		return pDAO.postCount(p, req, res);
	}
	@RequestMapping(value = "/reple.count", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	public @ResponseBody Reples repleCount(Reple r, HttpServletRequest req, HttpServletResponse res) {
		return pDAO.repleCount(r, req, res);
	}
	@RequestMapping(value = "/reple.delete", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody void repleDelete(Reple r, HttpServletRequest req, HttpServletResponse res) {
		pDAO.repleDelete(r, req, res);
	}
	@RequestMapping(value = "/post.delete", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody void postDelete(Post p, HttpServletRequest req, HttpServletResponse res) {
		
		Reple r = new Reple();
		r.setWr_rno(p.getWp_no());
		
		LikeBool lb = new LikeBool();
		lb.setWlb_pno(p.getWp_no());
		// 하트도 삭제
		pDAO.deleteLikeBoolByPno(lb, req, res);
		pDAO.deleteRepleByRno(r, req, res);
		pDAO.deletePostByNo(p, req, res);
	}
	@RequestMapping(value = "/like.update", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	public @ResponseBody LikeBools likeUpdate(LikeBool lb, Post p, HttpServletRequest req, HttpServletResponse res) {
		pDAO.updateLikeByNo(p, req, res);
		return pDAO.updateOrInsertLikeBool(lb, req, res);
	}
	@RequestMapping(value = "/like.cnt", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	public @ResponseBody Posts likeCnt(Post p, HttpServletRequest req, HttpServletResponse res) {
		return pDAO.likeCnt(p, req, res);
	}
}
