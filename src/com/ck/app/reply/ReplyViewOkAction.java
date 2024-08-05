package com.ck.app.reply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.ReplyDAO;
import com.ck.model.dto.ReplyDTO;

public class ReplyViewOkAction  implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long boardnum = Long.parseLong(req.getParameter("boardnum"));
		int maxNum = Integer.parseInt(req.getParameter("maxNum"));
		int pageSize = 10;
		String keyword = req.getParameter("keyword");
		String category = req.getParameter("category");
	
		ReplyDAO rdao = new ReplyDAO();
		List<ReplyDTO> reply =  rdao.getReplies(boardnum,maxNum,pageSize);

		req.setAttribute("replies",reply);
		
		Transfer transfer = new Transfer();
		transfer.setRedirect(false);
		transfer.setPath(req.getContextPath()+"/boardlist.bo?category="+category+"keyword="+keyword+"&boardnum="+boardnum);
		return transfer;
	}
}
