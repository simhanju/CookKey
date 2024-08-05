package com.ck.app.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.ChatDAO;
import com.ck.model.dto.ChatDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ChatListOkAction implements Action {
    @Override
    public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userid = (String) req.getSession().getAttribute("loginUser");
        System.out.println("아이디 : " + userid);

        ChatDAO cdao = new ChatDAO();
        List<ChatDTO> chat = cdao.getChatListByUserid(userid);
        System.out.println("채팅리스트");

        // 채팅 목록을 JSON 형식으로 변환
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        JsonArray chatlist = new JsonArray();
        		
        for(ChatDTO cdto : chat) {
        	chatlist.add(gson.toJsonTree(cdto));
        }

        json.add("chatlist", chatlist);
        String str = gson.toJson(json);
        // JSON 형식의 응답을 클라이언트에게 전송
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
    	out.print(str);
        
        return null; // Transfer 객체를 반환하지 않음 (채팅 목록을 JSON으로 반환하므로 페이지 이동은 필요하지 않음)
    }
}
