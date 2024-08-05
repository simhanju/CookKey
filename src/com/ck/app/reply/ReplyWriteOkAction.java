package com.ck.app.reply;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dto.ReplyDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ck.model.dao.ReplyDAO;

public class ReplyWriteOkAction implements Action{
   @Override
   public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
      long boardnum = Long.parseLong(req.getParameter("boardnum"));
      String replycontents = req.getParameter("replycontents");
      String userid = (String)req.getSession().getAttribute("loginUser");
      
      ReplyDTO reply = new ReplyDTO();
      reply.setBoardnum(boardnum);
      reply.setReplycontents(replycontents);
      reply.setUserid(userid);
      
      ReplyDAO rdao = new ReplyDAO();
      
        System.out.println("===================================");
        
        Gson gson = new Gson();
       JsonObject json = new JsonObject();
       JsonArray jarr = new JsonArray();
      
       resp.setCharacterEncoding("UTF-8");
      resp.setContentType("text/html; charset=utf-8");
      PrintWriter out = resp.getWriter();
       
      if(rdao.insertReply(reply)) {
           jarr.add(gson.toJsonTree("O"));
           System.out.println("성공");
      }
      else {
         jarr.add(gson.toJsonTree("X"));
      }
      json.add("success", jarr);
      
      out.print(json);
      
      return null;
   }
}