package com.ck.app.board;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.BoardDAO;

import com.ck.model.dao.FileDAO;
import com.ck.model.dto.BoardDTO;
import com.ck.model.dto.FileDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardUpdateOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String saveFolder = req.getServletContext().getRealPath("file");
		int size = (int)(1024*1024*1024*1.5);
		
		MultipartRequest multi = new MultipartRequest(req,saveFolder,size,"UTF-8",
				new DefaultFileRenamePolicy());
		//삭제해야 할 파일명들
		String updateCnt = multi.getParameter("updateCnt");
		System.out.println("updateCnt:"+updateCnt);

		long boardnum = Long.parseLong(multi.getParameter("boardnum"));
		System.out.println("보드넘 "+boardnum);
		String boardcontents = multi.getParameter("boardcontents");
		String userid = (String) req.getSession().getAttribute("loginUser");
		
		BoardDTO board = new BoardDTO();
		board.setBoardnum(boardnum);
		board.setBoardcontents(boardcontents);
		board.setUserid(userid);

		BoardDAO bdao = new BoardDAO();
		System.out.println("업뎃 완료");
		Transfer transfer = new Transfer();
		transfer.setRedirect(true);
		if(bdao.modifyBoard(board)) {
			transfer.setPath(req.getContextPath()+"/app/board/list.jsp");			
		}
		else {
			
		}
		
		return transfer;
		
	}
}
