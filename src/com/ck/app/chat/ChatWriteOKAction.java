package com.ck.app.chat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.ChatDAO;
import com.ck.model.dto.MessageDTO;

public class ChatWriteOKAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String writerID = req.getParameter("userid");
		String chatcontent = req.getParameter("chat-input");
		long chatID= Long.parseLong(req.getParameter("chatID"));
		
		
		MessageDTO msg = new MessageDTO();
		msg.setChatID(chatID);
		msg.setWriterID(writerID);
		msg.setChatcontent(chatcontent);
		
		ChatDAO cdao = new ChatDAO();
		cdao.sendMessage(msg);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		
		Transfer transfer = new Transfer();
		transfer.setRedirect(true);
		String queryString = String.format("?chatID="+chatID);
		transfer.setPath(req.getContextPath()+"/chatlist.ch : "+queryString);
		return transfer;
	}
}
