package com.ck.app.like;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.BoardDAO;
import com.ck.model.dto.LikeDTO;

public class LikeCancelOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		long boardnum = Long.parseLong(req.getParameter("boardnum"));
		String userid = req.getParameter("userid");
		String category = req.getParameter("category");
		String keyword = req.getParameter("keyword");
		
		BoardDAO bdao = new BoardDAO();
		LikeDTO ldto = new LikeDTO();
		ldto.setUserid(userid);
		ldto.setBoardnum(boardnum);
		
		PrintWriter out = resp.getWriter();
		out.print("<script>");
		if(bdao.deleteLike(ldto)) {
			
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
