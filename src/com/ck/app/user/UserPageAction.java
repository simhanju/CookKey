package com.ck.app.user;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.BoardDAO;
import com.ck.model.dao.FileDAO;
import com.ck.model.dao.UserDAO;
import com.ck.model.dto.BoardDTO;
import com.ck.model.dto.FileDTO;
import com.ck.model.dto.UserDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class UserPageAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. 요청 매개변수 값 가져오기
		String userid = req.getParameter("userid");
        System.out.println("아이디 : " + userid);
        long maxNum = 0;
        String maxNumParam = req.getParameter("maxNum");
		
		BoardDAO bdao = new BoardDAO();
        BoardDTO bdto = new BoardDTO();
		
        System.out.println("변수 넣기");
        
        if (maxNumParam != null && !maxNumParam.isEmpty()) {
            try {
               maxNum = Integer.parseInt(maxNumParam);
            } catch (NumberFormatException e) {
                System.err.println("Invalid maxNum parameter: " + maxNumParam);
            }
        } else {
        	bdto = bdao.getLastNum();
        	if(bdto == null) {
         	   
            }
            else {
         	   maxNum = bdto.getBoardnum()+1;
         	   System.err.println("maxNum parameter is null."+maxNum);        	   
            }
        }
        int pageSize = 6; // pageSize의 기본값 설정
        System.out.println("maxNum/pageSize 설정하기");

        List<BoardDTO> boardlist = bdao.getBoardListByUserid(userid, maxNum, pageSize);
        System.out.println("유저 보드 리스트");
        
        FileDTO fdto = new FileDTO();
        FileDAO fdao = new FileDAO();
        List<List<FileDTO>> boardfiles = new ArrayList<List<FileDTO>>();
        for (BoardDTO board : boardlist) {
        	fdto.setBoardnum(board.getBoardnum());
        	boardfiles.add(fdao.getFiles(fdto));
        }
        System.out.println("보드파일");
		
		UserDAO udao = new UserDAO();
		UserDTO user = udao.getUserById(userid);
		System.out.println("유저");
		
		FileDTO file = new FileDTO();
		List<FileDTO> userfile = new ArrayList<FileDTO>();
		file.setUserid(userid);
		userfile = fdao.getFiles(file);
		
		long friendamount = 0;
	
		System.out.println("친구 수");
		
        System.out.println("===================================");
		
        Gson gson = new Gson();
    	JsonObject json = new JsonObject();
    	JsonArray jsonboardlist = new JsonArray();
    	JsonArray jsonboardfiles = new JsonArray();
    	JsonArray jsonuser = new JsonArray();
    	JsonArray jsonuserfile = new JsonArray();
    	
    	for (BoardDTO board : boardlist) {
    		jsonboardlist.add(gson.toJsonTree(board));
        }
    	for (List<FileDTO> files : boardfiles) {
    		JsonArray jsonboardfile = new JsonArray();
    		for(FileDTO boardfile : files) {
    			jsonboardfile.add(gson.toJsonTree(boardfile));
    		}
    		jsonboardfiles.add(gson.toJsonTree(jsonboardfile));
    	}
    	
    	jsonuser.add(gson.toJsonTree(user));
    	jsonuserfile.add(gson.toJsonTree(userfile));
    	
    	json.add("boardlist", jsonboardlist);
    	json.add("boardfiles", jsonboardfiles);
    	json.add("user", jsonuser);
    	json.add("userfile", jsonuserfile);
    	
    	
    	
    	String str = gson.toJson(json);
    	
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/JSON");
    	PrintWriter out = resp.getWriter();
    	out.print(str);
        
		return null;
	}
}
