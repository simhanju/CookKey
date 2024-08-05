package com.ck.app.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.UserDAO;
import com.ck.model.dto.UserDTO;

public class UserLoginOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.setCharacterEncoding("UTF-8");
		//MIME 타입
		resp.setContentType("text/html; charset=utf-8");
		
		String userid = req.getParameter("userid");
		String userpw = req.getParameter("userpw");
		
		UserDAO udao = new UserDAO();
		UserDTO temp = udao.getUserById(userid);

		PrintWriter out = resp.getWriter();
		if(temp.getUserpw().equals(userpw)) {
			req.getSession().setAttribute("loginUser", userid);
			//메인으로 이동
			out.print("<script>");
			out.print("alert('"+userid+"님 어서오세요~!');");
			out.print("location.replace('"+req.getContextPath()+"/app/board/list.jsp')");
			out.print("</script>");
			
		}
		else {
			out.print("<script>");
			out.print("alert('로그인 실패!');");
			out.print("location.replace('"+req.getContextPath()+"/')");
			out.print("</script>");
		}
		
		
		return null;
	}
}








