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
	
	// �Խù� ����
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
			pDAO.searchPostByNo(p, req, res); //�Խù� ������ �� �ش� �Խù��� ����
			
			Reple r = new Reple();
			r.setWr_rno(p.getWp_no());
			pDAO.searchRepleByRNo(r, req, res); // ��� ����Ʈ
			
			//return "post/postDetail";
			Member m = new Member();
			m.setWm_id(p.getWp_id());
			req.setAttribute("waveMember", mDAO.MemberSearch(m, req, res)); // �Խù� ������ ����
			
			pDAO.searchPost(p, req, res); // �� ȸ���� �Խù��� ���
			req.setAttribute("postDetail", "post/postDetail.jsp");
			return "member/loginOK";
			
		}
		return "member/goToIndex";
	}
	@RequestMapping(value = "/post.reple.do", method = RequestMethod.GET)
	public String repleDo(Reple r, HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("contentArea", "home.jsp");
		
		if(mDAO.loginCheck(req, res)) {
			pDAO.insertReple(r, req, res); // ��� �߰�
			
			//Reple r1 = pDAO.searchRepleByRNo2(r, req, res); // �ش� ����� ����; �̰� �����ε�
			
			Post p1 = new Post();
			p1.setWp_no(r.getWr_rno());
			Post p2 = pDAO.searchPostByNo3(p1, req, res); // �Խù� ������ ����
			
			Member m = (Member) req.getSession().getAttribute("loginMember");
			
			// �Խù� ���ΰ� ��� �� ��� ��ġ ����
			if(p2.getWp_id().equals(m.getWm_id())) {
				req.setAttribute("memberTitle", "loginOkTitle.jsp");			
			} else {
				req.setAttribute("memberTitle", "memberTitle.jsp");			
			}
			
			//Post p = new Post();
			//p.setWp_no(r.getWr_rno());
			pDAO.searchPostByNo(p2, req, res); // �Խù��� ��µ� �Խù� ������ ����
			
			pDAO.searchRepleByRNo(r, req, res); // �ش� �Խù����� ��� �� ��� ����
			
			
			//p.setWp_id(m.getWm_id()); // �����ʿ�
			pDAO.searchPost(p2, req, res); // ��� �Խù�; �����ʿ�
			
			Member wm = new Member();
			wm.setWm_id(p2.getWp_id());
			req.setAttribute("waveMember", mDAO.MemberSearch(wm, req, res)); // ��� �� ��� ����; �����ʿ�
			
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
		// ��Ʈ�� ����
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
