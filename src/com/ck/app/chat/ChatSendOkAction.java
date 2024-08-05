package com.ck.app.chat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.ChatDAO;
import com.ck.model.dto.MessageDTO;

public class ChatSendOkAction implements Action {
    @Override
    public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("여긴 올걸");
        String chatIDParam = req.getParameter("chatID");
        System.out.println(chatIDParam);
        if (chatIDParam != null && !chatIDParam.isEmpty()) {
            long chatID = Long.parseLong(chatIDParam);
            
            String message = req.getParameter("message"); 
            String userid = (String) req.getSession().getAttribute("loginUser"); 
            System.out.println(userid);
            System.out.println(message);
            System.out.println(chatID);
            MessageDTO messageDTO = new MessageDTO(); 
            messageDTO.setChatID(chatID);
            messageDTO.setChatcontent(message);
            messageDTO.setWriterID(userid);

            ChatDAO cdao = new ChatDAO();

            if (cdao.sendMessage(messageDTO)) { 
                
            } else {
              
            }

            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=utf-8");
        } else {
            System.out.println("그럼 여기로 와 ?");
        }

        Transfer transfer = new Transfer();
        transfer.setRedirect(false);
        transfer.setPath("/app/chat/chatlist.jsp");
        return transfer;
    }
}
