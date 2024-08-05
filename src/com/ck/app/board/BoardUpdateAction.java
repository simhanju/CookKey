package com.ck.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.BoardDAO;

import com.ck.model.dao.FileDAO;
import com.ck.model.dto.BoardDTO;
import com.ck.model.dto.FileDTO;

public class BoardUpdateAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long boardnum = Long.parseLong(req.getParameter("boardnum"));
		System.out.println("오긴 함?");
		BoardDTO bdto = new BoardDTO();
		BoardDAO bdao = new BoardDAO();
		bdto.setBoardnum(boardnum);
		FileDTO fdto = new FileDTO();
		FileDAO fdao = new FileDAO();
		fdto.setBoardnum(boardnum);
		
		req.setAttribute("board", bdao.getBoardByBoardNum(boardnum));
		req.setAttribute("files",fdao.getBoardFile(boardnum));
		
		Transfer transfer = new Transfer();
		transfer.setRedirect(false);
		transfer.setPath("/app/board/modify.jsp"); 
		return transfer;
	}
}
