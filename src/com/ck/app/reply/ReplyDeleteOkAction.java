package com.ck.app.reply;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.ReplyDAO;
import com.ck.model.dto.ReplyDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ReplyDeleteOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long replynum = Long.parseLong(req.getParameter("replynum"));
	    long boardnum = Long.parseLong(req.getParameter("boardnum"));

		System.out.println(replynum);
		System.out.println(boardnum);
		
		ReplyDAO rdao = new ReplyDAO();
		ReplyDTO rdto = new ReplyDTO();
		rdto.setReplynum(replynum);
	
		 System.out.println("===================================");
		 
		 Gson gson = new Gson();
		 JsonObject json = new JsonObject();
		 
		if(rdao.deleteReply(replynum)) {
			json.add("success", gson.toJsonTree("O"));
		}
		else {
			json.add("success", gson.toJsonTree("X"));
		}
		
	     resp.setCharacterEncoding("UTF-8");
	        resp.setContentType("application/JSON");
	        PrintWriter out = resp.getWriter();

	        String str = gson.toJson(json);
	    	out.print(str);
	    	System.out.println("삭제완료?");
		return null;
	}
}
