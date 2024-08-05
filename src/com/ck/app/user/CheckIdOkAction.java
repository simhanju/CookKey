package com.ck.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.UserDAO;
import com.ck.model.dto.UserDTO;

public class CheckIdOkAction implements Action {
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userid = req.getParameter("userid");
		
		UserDAO udao = new UserDAO();
		UserDTO temp = udao.getUserById(userid);
		
		PrintWriter out = resp.getWriter();
		
		if(temp == null) {
			out.print("O");
		}
		else {
			out.print("X");
		}
		return null;
	}
}










