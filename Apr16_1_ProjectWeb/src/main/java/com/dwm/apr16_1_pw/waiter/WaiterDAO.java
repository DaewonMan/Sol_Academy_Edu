package com.dwm.apr16_1_pw.waiter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaiterDAO {
	@Autowired private SqlSession ss;
	
	public Waiters countWaitForMe(Waiter w, HttpServletRequest req, HttpServletResponse res) {
		return new Waiters(ss.getMapper(WaiterMapper.class).countWaitForMe(w));
	}
	
	public Waiters followWaitOrNot(Waiter w, HttpServletRequest req, HttpServletResponse res) {
		return new Waiters(ss.getMapper(WaiterMapper.class).followWaitOrNot(w));
	}
	
	public void deleteWaiter(Waiter w, HttpServletRequest req, HttpServletResponse res) {	
		try {
			if(ss.getMapper(WaiterMapper.class).deleteWaiter(w) == 1) {
				System.out.println("승인 해제 성공");
				res.getWriter().print("OK");	
			} else {
				System.out.println("승인 해제 실패");
				res.getWriter().print("FAIL");	
				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void insertWaiter(Waiter w, HttpServletRequest req, HttpServletResponse res) {	
		try {
			if(ss.getMapper(WaiterMapper.class).insertWaiter(w) == 1) {
				System.out.println("팔로우 신청 성공");
				res.getWriter().print("OK");	
			} else {
				System.out.println("팔로우 신청 실패");
				res.getWriter().print("FAIL");	
				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
