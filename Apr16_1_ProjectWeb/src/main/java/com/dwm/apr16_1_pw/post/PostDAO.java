package com.dwm.apr16_1_pw.post;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwm.apr16_1_pw.member.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class PostDAO {
	@Autowired private SqlSession ss;
	
	public Posts likeCnt(Post p, HttpServletRequest req, HttpServletResponse res) {	
		return new Posts(ss.getMapper(PostMapper.class).searchPostByNo2(p));
	}
	
	public LikeBools updateOrInsertLikeBool(LikeBool lb, HttpServletRequest req, HttpServletResponse res) {	
		
		try {
			LikeBools lbs = searchLikeBoolByIdPno(lb, req, res);
			int sss = 0;
			
			if(lbs.getLikebool().size() > 0) {
				int temp = lb.getWlb_bool().intValue(); // bigdecimal to int
				if(temp == 0) { // 게시물 눌렀을 때; 하트버튼을 눌러 업데이트가 아닌
					lb.setWlb_bool(lbs.getLikebool().get(0).getWlb_bool()); // 기존에 bool값 할당
				}
				sss = ss.getMapper(PostMapper.class).updateLikeBoolByIdPno(lb); // bool 할당		
			} else {
				sss = ss.getMapper(PostMapper.class).insertLikeBoolById(lb); // 새로운 게시물 들어 갈 떄
			}
			
			if(sss == 1) {
				return lbs;
			} else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public LikeBools searchLikeBoolByIdPno(LikeBool lb, HttpServletRequest req, HttpServletResponse res) {	
		return new LikeBools(ss.getMapper(PostMapper.class).searchLikeBoolByIdPno(lb));
		
	}
	
	public void updateLikeByNo(Post p, HttpServletRequest req, HttpServletResponse res) {	
		try {
			if (ss.getMapper(PostMapper.class).updateLikeByNo(p) == 1) {
				System.out.println("좋아요업뎃 성공");
			} else {
				res.getWriter().print("FAIL");				
			}
			
		} catch (Exception ex) {
			System.out.println("오류: 좋아요 하는데 문제가 발생했습니다.");
		}
		
	}
	
	public void deletePostByNo(Post p, HttpServletRequest req, HttpServletResponse res) {	
		try {
			if (ss.getMapper(PostMapper.class).deletePostByNo(p) == 1) {
				res.getWriter().print("OK");
				//System.out.println("성공");
			} else {
				res.getWriter().print("FAIL");
				//System.out.println("실패");
			}
		} catch (Exception ex) {
			System.out.println("오류: 게시물 삭제에 문제가 발생했습니다.");
		}
		
	}
	public void deleteLikeBoolByPno(LikeBool lb, HttpServletRequest req, HttpServletResponse res) {	
		try {
			ss.getMapper(PostMapper.class).deleteLikeBoolByPno(lb);
		} catch (Exception ex) {
			System.out.println("오류: 댓글 삭제에 문제가 발생했습니다.");
		}
		
	}
	
	public void deleteRepleByRno(Reple r, HttpServletRequest req, HttpServletResponse res) {	
		try {
			if (ss.getMapper(PostMapper.class).deleteRepleByRno(r) >= 1) {
				//res.getWriter().print("OK");
				System.out.println("성공");
			} else {
				//res.getWriter().print("FAIL");
				System.out.println("실패");
			}
		} catch (Exception ex) {
			System.out.println("오류: 댓글 삭제에 문제가 발생했습니다.");
		}
		
	}
	
	public void repleDelete(Reple r, HttpServletRequest req, HttpServletResponse res) {	
		try {
            if (ss.getMapper(PostMapper.class).deleteRepleByNo(r) == 1) {
               res.getWriter().print("OK");
            } else {
               res.getWriter().print("FAIL");
            }
        } catch (Exception ex) {
            System.out.println("오류: 댓글 삭제에 문제가 발생했습니다.");
        }

	}
	
	public Reples repleCount(Reple r, HttpServletRequest req, HttpServletResponse res) {	
		return new Reples(ss.getMapper(PostMapper.class).searchRepleByRNo(r));
	}
	
	public Posts postCount(Post p, HttpServletRequest req, HttpServletResponse res) {	
		return new Posts(ss.getMapper(PostMapper.class).searchPost(p));
	}
	
	public void insertReple(Reple r, HttpServletRequest req, HttpServletResponse res) {	
		Member m = (Member) req.getSession().getAttribute("loginMember"); // 회원 정보
		r.setWr_id(m.getWm_id());
		
		if(ss.getMapper(PostMapper.class).insertReple(r) == 1) {
			req.setAttribute("r", "리플 달기 성공");	
		} else {
			req.setAttribute("r", "리플 달기 실패");			
		}
		
		
	}
	public void searchRepleByRNo(Reple r, HttpServletRequest req, HttpServletResponse res) {	
		List<Reple> r1 = ss.getMapper(PostMapper.class).searchRepleByRNo(r);
		req.setAttribute("reples", r1);
	}
	public Reple searchRepleByRNo2(Reple r, HttpServletRequest req, HttpServletResponse res) {	
		return ss.getMapper(PostMapper.class).searchRepleByRNo2(r);
	}
	public void searchPostByNo(Post p, HttpServletRequest req, HttpServletResponse res) {	
		Post p1 = ss.getMapper(PostMapper.class).searchPostByNo(p);
		req.setAttribute("postDetails", p1);
		
	}
	public Post searchPostByNo3(Post p, HttpServletRequest req, HttpServletResponse res) {	
		return ss.getMapper(PostMapper.class).searchPostByNo3(p);
	}
	public void searchPost(Post p, HttpServletRequest req, HttpServletResponse res) {	
		List<Post> p1 = ss.getMapper(PostMapper.class).searchPost(p);
		req.setAttribute("posts", p1);
		
	}
	public void addPost(Member m, Post p, HttpServletRequest req, HttpServletResponse res) {
		try {

			String path = req.getSession().getServletContext().getRealPath("resources/img");
			System.out.println(path);
			MultipartRequest mr = new MultipartRequest(req, path, 30 * 1024 * 1024, "euc-kr",
					new DefaultFileRenamePolicy());
			
			String wp_id = m.getWm_id();
			String wp_title = mr.getParameter("wp_title");
			String wp_hash = mr.getParameter("wp_hash");

			String wp_img = mr.getFilesystemName("wp_img");
			wp_img = URLEncoder.encode(wp_img, "euc-kr");
			wp_img = wp_img.replace("+", " ");

			p.setWp_id(wp_id);
			p.setWp_title(wp_title);
			p.setWp_hash(wp_hash);
			p.setWp_img(wp_img);
			

			if (ss.getMapper(PostMapper.class).addPost(p) == 1) {
				req.setAttribute("r", "게시물 성공");
			} else {
				req.setAttribute("r", "게시물 실패");

			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "DB 문제");
		}
	}
}
