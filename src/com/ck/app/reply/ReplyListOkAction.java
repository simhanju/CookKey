package com.ck.app.reply;

import java.io.PrintWriter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.ReplyDAO;
import com.ck.model.dto.ReplyDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ReplyListOkAction implements Action{
   @Override
   public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
      long boardnum = Long.parseLong(req.getParameter("boardnum"));
      System.out.println(boardnum);
      long maxNum = 0;
        String maxNumParam = req.getParameter("maxnum");
        System.out.println("맥스넘파람 : " + maxNumParam);
        
        ReplyDTO rdto = new ReplyDTO();
        ReplyDAO rdao = new ReplyDAO();
        if (maxNumParam != null && !maxNumParam.isEmpty() && !maxNumParam.equals("0")) {
            try {
               maxNum = Integer.parseInt(maxNumParam);
            } catch (NumberFormatException e) {
                System.err.println("Invalid maxNum parameter: " + maxNumParam);
            }
        } else {
           rdto = rdao.getLastReplyOfBoardByBoardnum(boardnum);
           
           if(rdto == null) {
        	   
           }else {
        	   maxNum = rdto.getReplynum()+1;
        	   System.err.println("maxNum parameter is null."+maxNum);
           }
        }
        System.out.println(maxNum);
        int pageSize = 10; // pageSize의 기본값 설정
        System.out.println("maxNum/pageSize 설정하기");
        
      
      List<ReplyDTO> replies = rdao.getReplies(boardnum, maxNum, pageSize);
      
      System.out.println("===================================");
      Gson gson = new Gson();
      JsonObject json = new JsonObject();
      JsonArray jsonreplies = new JsonArray();
      
      
    jsonreplies.add(gson.toJsonTree(replies));
    	  
      
      
      json.add("replies", jsonreplies);
      String str = gson.toJson(json);
      resp.setCharacterEncoding("UTF-8");
      resp.setContentType("text/html; charset=utf-8");
      PrintWriter out = resp.getWriter();
      out.print(str);
      
      return null;
   }
}