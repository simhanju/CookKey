package com.ck.app.board;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.BoardDAO;
import com.ck.model.dao.CategoryDAO;
import com.ck.model.dao.FileDAO;
import com.ck.model.dao.ReplyDAO;
import com.ck.model.dto.CategoryDTO;
import com.ck.model.dto.FileDTO;
import com.ck.model.dto.ReplyDTO;

public class BoardDeleteOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long boardnum = Long.parseLong(req.getParameter("boardnum"));
		System.out.println("보드넘이 문젠가 : "+ boardnum);
		
		BoardDAO bdao = new BoardDAO();
		
		Transfer transfer = new Transfer();
		transfer.setRedirect(true);
		
		FileDTO fdto = new FileDTO();
		String saveFolder = req.getServletContext().getRealPath("file");
		FileDAO fdao = new FileDAO();
		ReplyDTO rdto = new ReplyDTO();
		ReplyDAO rdao = new ReplyDAO();
		CategoryDTO cdto = new CategoryDTO();
		CategoryDAO cdao = new CategoryDAO();
		cdto.setBoardnum(boardnum);
		fdto.setBoardnum(boardnum);
		
		System.out.println("그럼 여기서 막히나보다");
		List<FileDTO> files = fdao.getFiles(fdto);
		System.out.println(files);
		
		List<CategoryDTO> categories = cdao.getCategories(cdto);
		for (int i = 0; i < categories.size(); i++) {
			cdao.deleteCategory(categories.get(i));
		}
	
		fdao.deleteByBoardNum(boardnum);
		System.out.println("파일 삭제 성공");
	
		rdao.deleteReply(boardnum);
		System.out.println("댓글 삭제 성공...?");
		System.out.println("여기까지 하면 그래도 된거같은");
		if(bdao.deleteBoard(boardnum)) {		
			transfer.setPath(req.getContextPath()+"/app/board/list.jsp");
		}
		else {
			transfer.setPath(req.getContextPath()+"/app/board/list.jsp");
		}
		
		return transfer;
	}
}
