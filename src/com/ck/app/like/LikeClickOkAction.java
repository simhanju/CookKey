package com.ck.app.like;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.BoardDAO;
import com.ck.model.dto.LikeDTO;

public class LikeClickOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long boardnum = Long.parseLong(req.getParameter("boardnum"));
		String userid = (String)req.getSession().getAttribute("loginUser");
		String category = req.getParameter("categorty");
		String keyword = req.getParameter("keyword");

		LikeDTO ldto = new LikeDTO();
		ldto.setBoardnum(boardnum);
		ldto.setUserid(userid);
		
		BoardDAO bdao = new BoardDAO();
		
		PrintWriter out = resp.getWriter();
		out.print("<script>");
		if(bdao.insertLike(ldto)) {
			
		}
		else {
			
		}
		String format = String.format("location.replace('%s/boardlist.bo?boardnum=%s&category=%s&keyword=%s')",
				req.getContextPath(),boardnum+"",category,keyword);
		out.print(format);
		out.print("</script>");
		return null;
	}
}
