package com.ck.app.board;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.BoardDAO;
import com.ck.model.dao.FileDAO;
import com.ck.model.dao.ReplyDAO;
import com.ck.model.dao.UserDAO;
import com.ck.model.dto.BoardBookmarkDTO;
import com.ck.model.dto.BoardDTO;
import com.ck.model.dto.FileDTO;
import com.ck.model.dto.LikeDTO;
import com.ck.model.dto.ReplyDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class BoardBookmarkListOkAction implements Action{
   @Override
    public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. 요청 매개변수 값 가져오기
      String userid = (String)req.getSession().getAttribute("loginUser");
        System.out.println("아이디 : " + userid);
        BoardDAO bdao = new BoardDAO();
        BoardDTO bdto = new BoardDTO();
        long maxNum = 0;
        String maxNumParam = req.getParameter("maxNum");
        
        System.out.println("변수 넣기");
        
        if (maxNumParam != null && !maxNumParam.isEmpty() && !maxNumParam.equals("0")) {
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
        int pageSize = 10; // pageSize의 기본값 설정
        System.out.println("maxNum/pageSize 설정하기");

        
        List<BoardDTO> timeline = bdao.getBookmarkBoardListByUserid(userid, maxNum, pageSize);
         System.out.println("북마크 리스트");

        

        // 3. 파일 데이터 가져오기
         FileDTO fdto = new FileDTO();
         FileDAO fdao = new FileDAO();
         List<List<FileDTO>> boardfiles = new ArrayList<List<FileDTO>>();
         List<List<FileDTO>> userfiles = new ArrayList<List<FileDTO>>();
         UserDAO udao = new UserDAO();
         for (BoardDTO board : timeline) {
         	fdto.setBoardnum(board.getBoardnum());
         	boardfiles.add(fdao.getFiles(fdto));

         	FileDTO file = new FileDTO();
         	file.setUserid(board.getUserid());
         	List<FileDTO> userfile = new ArrayList<FileDTO>();
         	userfile = fdao.getFiles(file);
         	userfiles.add(userfile);
         }
         System.out.println("보드파일");
 		System.out.println("유저파일");
         
        // 4. 댓글 데이터 가져오기
        ReplyDAO rdao = new ReplyDAO();
        List<ReplyDTO> lreply = new ArrayList<ReplyDTO>();
        for (BoardDTO board : timeline) {
            lreply.add(rdao.getLastReplyOfBoardByBoardnum(board.getBoardnum()));
        }
        System.out.println("댓글");
        
        //5. 좋아요 개수 가져오기
        LikeDTO ldto = new LikeDTO();
        ArrayList<Long> likes = new ArrayList<Long>(); 
        for(BoardDTO board : timeline) {
           likes.add(bdao.getLikeCnt(board.getBoardnum()));
        }
        System.out.println("좋아요");

        ArrayList<LikeDTO> mylikes = new ArrayList<LikeDTO>();
        for (BoardDTO board : timeline) {
        	LikeDTO like = new LikeDTO();
        	like.setBoardnum(board.getBoardnum());
        	like.setUserid(userid);
        	mylikes.add(bdao.getMyLikeOfBoardByUseridAndBoardnum(like));
        }
        System.out.println("좋아요 여부");
        
        //7. 북마크 여부 가져오기
        List<BoardBookmarkDTO> mybookmarks = new ArrayList<BoardBookmarkDTO>();
        for (BoardDTO board : timeline) {
        	BoardBookmarkDTO boardbookmark = new BoardBookmarkDTO();
        	boardbookmark.setBoardnum(board.getBoardnum());
        	boardbookmark.setUserid(userid);
        	mybookmarks.add(bdao.getMyBoardBookmarkByUseridAndBoardnum(boardbookmark));
        }
        System.out.println("북마크 여부");
        System.out.println("===================================");
        
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        JsonArray jsontimeline = new JsonArray();
        JsonArray jsonlikes = new JsonArray();
        JsonArray jsonlreply = new JsonArray();
    		JsonArray jsonboardfiles = new JsonArray();
    		JsonArray jsonuserfiles = new JsonArray();
    		JsonArray jsonmylikes = new JsonArray();
        JsonArray jsonmybookmarks = new JsonArray();
        
        for (BoardDTO board : timeline) {
           jsontimeline.add(gson.toJsonTree(board));
         }
    	for (List<FileDTO> bfiles : boardfiles) {
 		JsonArray jsonboardfile = new JsonArray();
 		for(FileDTO boardfile : bfiles) {
 			jsonboardfile.add(gson.toJsonTree(boardfile));
 		}
 		jsonboardfiles.add(gson.toJsonTree(jsonboardfile));
 	}
 	
 	for (List<FileDTO> ufiles : userfiles) {
 		JsonArray jsonuserfile = new JsonArray();
 		for(FileDTO userfile : ufiles) {
 			jsonuserfile.add(gson.toJsonTree(userfile));
 		}
 		jsonuserfiles.add(gson.toJsonTree(jsonuserfile));
 	}
        
        jsonlikes.add(gson.toJsonTree(likes));
        jsonlreply.add(gson.toJsonTree(lreply));
    		jsonmylikes.add(gson.toJsonTree(mylikes));
        jsonmybookmarks.add(gson.toJsonTree(mybookmarks));
        
        json.add("timeline", jsontimeline);
        json.add("likes", jsonlikes);
        json.add("lreply", jsonlreply);
    		json.add("boardfiles", jsonboardfiles);
    		json.add("userfiles", jsonuserfiles);
    		json.add("mylikes", jsonmylikes);
    		json.add("mybookmarks", jsonmybookmarks);
        
        String str = gson.toJson(json);
        
       resp.setCharacterEncoding("UTF-8");
       resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(str);
         
         return null;
}
}