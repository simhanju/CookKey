package com.ck.app.chat;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.ChatDAO;
import com.ck.model.dto.MessageDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ChatViewOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String chatIDParam = req.getParameter("chatID");
		System.out.println(chatIDParam);
		
		if (chatIDParam != null && !chatIDParam.isEmpty()) {
		    long chatID = Long.parseLong(chatIDParam);
		    String loginUser = (String)req.getSession().getAttribute("loginUser");
			
			
			ChatDAO cdao = new ChatDAO();
			
			List<MessageDTO> message = cdao.getMessageByChatID(chatID);
			System.out.println("메세지 리스트");
			
			Gson gson = new Gson();
			JsonObject json = new JsonObject();
			JsonArray messageList = new JsonArray();
			
			for(MessageDTO mdto : message) {
				messageList.add(gson.toJsonTree(mdto));
				messageList.add(gson.toJsonTree(chatID));
				
			}
			
			json.add("messageList", messageList);
			String str = gson.toJson(json);
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print(str);
		} else {
	
		}
        
        return null;
	}
}
