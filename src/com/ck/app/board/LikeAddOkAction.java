package com.ck.app.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.BoardDAO;
import com.ck.model.dto.LikeDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class LikeAddOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userid = (String)req.getSession().getAttribute("loginUser");
        System.out.println("아이디 : " + userid);
        long boardnum = Long.parseLong(req.getParameter("num"));
        System.out.println(boardnum);
        
        BoardDAO bdao = new BoardDAO();
        LikeDTO like = new LikeDTO();
        like.setUserid(userid);
        like.setBoardnum(boardnum);
        
        System.out.println("===================================");
        
        Gson gson = new Gson();
    	JsonObject json = new JsonObject();
    	
        if(bdao.insertLike(like)) {
        	json.add("success", gson.toJsonTree("O"));
        }
        else {
        	json.add("success", gson.toJsonTree("X"));
        }
        long likeCnt = bdao.getLikeCnt(boardnum);
        
        json.add("likeCnt", gson.toJsonTree(likeCnt));
        
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();

        String str = gson.toJson(json);
    	out.print(str);
        
		return null;
	}
}
