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
				if(temp == 0) { // �Խù� ������ ��; ��Ʈ��ư�� ���� ������Ʈ�� �ƴ�
					lb.setWlb_bool(lbs.getLikebool().get(0).getWlb_bool()); // ������ bool�� �Ҵ�
				}
				sss = ss.getMapper(PostMapper.class).updateLikeBoolByIdPno(lb); // bool �Ҵ�		
			} else {
				sss = ss.getMapper(PostMapper.class).insertLikeBoolById(lb); // ���ο� �Խù� ��� �� ��
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
				System.out.println("���ƿ���� ����");
			} else {
				res.getWriter().print("FAIL");				
			}
			
		} catch (Exception ex) {
			System.out.println("����: ���ƿ� �ϴµ� ������ �߻��߽��ϴ�.");
		}
		
	}
	
	public void deletePostByNo(Post p, HttpServletRequest req, HttpServletResponse res) {	
		try {
			if (ss.getMapper(PostMapper.class).deletePostByNo(p) == 1) {
				res.getWriter().print("OK");
				//System.out.println("����");
			} else {
				res.getWriter().print("FAIL");
				//System.out.println("����");
			}
		} catch (Exception ex) {
			System.out.println("����: �Խù� ������ ������ �߻��߽��ϴ�.");
		}
		
	}
	public void deleteLikeBoolByPno(LikeBool lb, HttpServletRequest req, HttpServletResponse res) {	
		try {
			ss.getMapper(PostMapper.class).deleteLikeBoolByPno(lb);
		} catch (Exception ex) {
			System.out.println("����: ��� ������ ������ �߻��߽��ϴ�.");
		}
		
	}
	
	public void deleteRepleByRno(Reple r, HttpServletRequest req, HttpServletResponse res) {	
		try {
			if (ss.getMapper(PostMapper.class).deleteRepleByRno(r) >= 1) {
				//res.getWriter().print("OK");
				System.out.println("����");
			} else {
				//res.getWriter().print("FAIL");
				System.out.println("����");
			}
		} catch (Exception ex) {
			System.out.println("����: ��� ������ ������ �߻��߽��ϴ�.");
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
            System.out.println("����: ��� ������ ������ �߻��߽��ϴ�.");
        }

	}
	
	public Reples repleCount(Reple r, HttpServletRequest req, HttpServletResponse res) {	
		return new Reples(ss.getMapper(PostMapper.class).searchRepleByRNo(r));
	}
	
	public Posts postCount(Post p, HttpServletRequest req, HttpServletResponse res) {	
		return new Posts(ss.getMapper(PostMapper.class).searchPost(p));
	}
	
	public void insertReple(Reple r, HttpServletRequest req, HttpServletResponse res) {	
		Member m = (Member) req.getSession().getAttribute("loginMember"); // ȸ�� ����
		r.setWr_id(m.getWm_id());
		
		if(ss.getMapper(PostMapper.class).insertReple(r) == 1) {
			req.setAttribute("r", "���� �ޱ� ����");	
		} else {
			req.setAttribute("r", "���� �ޱ� ����");			
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
				req.setAttribute("r", "�Խù� ����");
			} else {
				req.setAttribute("r", "�Խù� ����");

			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "DB ����");
		}
	}
}
