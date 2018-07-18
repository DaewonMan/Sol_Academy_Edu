package com.dwm.apr16_1_pw.follow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowDAO {
	@Autowired private SqlSession ss;
	
	public Follows followerCnt(Follow f, HttpServletRequest req, HttpServletResponse res) {
		return new Follows(ss.getMapper(FollowMapper.class).followerCnt(f));
	}
	
	public Follows followCnt(Follow f, HttpServletRequest req, HttpServletResponse res) {
		return new Follows(ss.getMapper(FollowMapper.class).followCnt(f));
	}
	
	public Follows followOrNot(Follow f, HttpServletRequest req, HttpServletResponse res) {
		return new Follows(ss.getMapper(FollowMapper.class).followOrNot(f));
	}
	public void insertFollower(Follow f, HttpServletRequest req, HttpServletResponse res) {
		try {
			if(ss.getMapper(FollowMapper.class).insertFollower(f) == 1) {
				res.getWriter().print("OK");
			} else {
				res.getWriter().print("FAIL");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteFollower(Follow f, HttpServletRequest req, HttpServletResponse res) {
		try {
			if(ss.getMapper(FollowMapper.class).deleteFollower(f) == 1) {
				res.getWriter().print("OK");
			} else {
				res.getWriter().print("FAIL");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
