package com.ck.app.reply;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.ReplyDAO;
import com.ck.model.dto.ReplyDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ReplyUpdateOkAction  implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long replynum = Long.parseLong(req.getParameter("replynum"));
		long boardnum = Long.parseLong(req.getParameter("boardnum"));
		String replycontents = req.getParameter("replycontents");

		System.out.println(replynum);
		System.out.println(boardnum);
		
		ReplyDTO reply = new ReplyDTO();
		reply.setReplycontents(replycontents);
		reply.setReplynum(replynum);
		
		
		ReplyDAO rdao = new ReplyDAO();
		System.out.println("되야하는디 그래야 자는디");
		
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		JsonArray jarr = new JsonArray();
		
		  resp.setCharacterEncoding("UTF-8");
	      resp.setContentType("text/html; charset=utf-8");
	      PrintWriter out = resp.getWriter();


		if(rdao.updateReply(reply)) {
			jarr.add(gson.toJsonTree("O"));
			System.out.println("업뎃완료");
		}
		else {
			  jarr.add(gson.toJsonTree("X"));
		}
		
		 json.add("success", jarr);
	      
	      out.print(json);
	      
	      return null;

	}
}
