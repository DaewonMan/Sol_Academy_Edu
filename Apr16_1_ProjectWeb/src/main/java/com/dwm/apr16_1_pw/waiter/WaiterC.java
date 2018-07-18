package com.dwm.apr16_1_pw.waiter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WaiterC {
	@Autowired private WaiterDAO wDAO;
	
	@RequestMapping(value = "/count.waitforme", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	public @ResponseBody Waiters countWaitForMe(Waiter w, HttpServletRequest req, HttpServletResponse res) {
		return wDAO.countWaitForMe(w, req, res);
	}
	
	@RequestMapping(value = "/followWait.ornot", method = RequestMethod.GET, produces = "application/xml; charset=utf-8")
	public @ResponseBody Waiters followWaitOrNot(Waiter w, HttpServletRequest req, HttpServletResponse res) {
		return wDAO.followWaitOrNot(w, req, res);
	}
	
	@RequestMapping(value = "/delete.waiter", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody void deleteWaiter(Waiter w, HttpServletRequest req, HttpServletResponse res) {
		wDAO.deleteWaiter(w, req, res);
	}
	@RequestMapping(value = "/insert.waiter", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody void insertWaiter(Waiter w, HttpServletRequest req, HttpServletResponse res) {
		wDAO.insertWaiter(w, req, res);
	}
}
