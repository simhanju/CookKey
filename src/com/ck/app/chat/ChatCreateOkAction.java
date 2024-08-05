package com.ck.app.chat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.ChatDAO;
import com.ck.model.dto.ChatDTO;

public class ChatCreateOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userid_1 = req.getParameter("loginUser");
		String userid_2 = req.getParameter("receive");

		ChatDTO cdto = new ChatDTO();
		cdto.setUserid_1(userid_1);
		cdto.setUserid_2(userid_2);
		
		System.out.println(cdto.getUserid_1());
		System.out.println(cdto.getUserid_2());
		
		ChatDAO cdao = new ChatDAO();
		Transfer transfer = new Transfer();
		
		try {
			cdao.createChat(cdto);
		} catch (Exception e) {
		}

		transfer.setRedirect(true);
		transfer.setPath(req.getContextPath()+"/app/chat/chatlist.jsp");
		return transfer;
	}
}
