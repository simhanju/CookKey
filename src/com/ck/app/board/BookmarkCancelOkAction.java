package com.ck.app.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.BoardDAO;
import com.ck.model.dto.BoardBookmarkDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class BookmarkCancelOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userid = (String)req.getSession().getAttribute("loginUser");
        System.out.println("아이디 : " + userid);
        long boardnum = Long.parseLong(req.getParameter("num"));
        System.out.println(boardnum);
        
        BoardDAO bdao = new BoardDAO();
        BoardBookmarkDTO boardbookmark = new BoardBookmarkDTO();
        boardbookmark.setUserid(userid);
        boardbookmark.setBoardnum(boardnum);
        
        System.out.println("===================================");
        
        Gson gson = new Gson();
    	JsonObject json = new JsonObject();
    	
        if(bdao.deleteBoardBookmark(boardbookmark)) {
        	json.add("success", gson.toJsonTree("O"));
        }
        else {
        	json.add("success", gson.toJsonTree("X"));
        }
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();

        String str = gson.toJson(json);
    	out.print(str);
        
		return null;
	}
}
