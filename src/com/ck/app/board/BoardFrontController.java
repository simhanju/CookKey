package com.ck.app.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Transfer;
import com.mysql.cj.Session;

public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doProcess(req, resp);
	}
	
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		System.out.println(command);

		Transfer transfer = null;
		switch(command) {
		case "/boardlist.bo":
			try {
				new BoardListOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/boardlist.bo : "+e);
			}
			break;
////		case "boardlistok.bo":
		case "/boardwrite.bo":
			try {
				new BoardWriteAction().execute(req, resp);
			} catch (Exception e) {
				System.out.println("/boardwrite.bo : "+e);
			}
			break;
		case "/boardwriteok.bo":
			try {
				transfer = new BoardWriteOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/boardwriteok.bo : "+e);
			}
			break;

		case "/boarddelete.bo":
			try {
				transfer = new BoardDeleteOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/boarddelete.bo : "+e);
			}
			break;
		case "/boardbookmarklist.bo":
			try {
				new BoardBookmarkListOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/boardbookmarklist.bo : "+e);
			}
			break;	
		case "/boardupdate.bo":
			try {
				transfer = new BoardUpdateAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/boardupdate.bo : "+e);
			}
			break;
		case "/boardupdateok.bo":
			try {
				transfer = new BoardUpdateOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/boardupdateok.bo : "+e);
			}
			break;
		case "/boardsearch.bo":
			try {
				new BoardSearchOkAction().execute(req, resp);
			} catch (Exception e) {
				System.out.println("/boardsearchok.bo : "+e);
			}
			break;
		case "/likeAdd.bo":
			try {
				new LikeAddOkAction().execute(req, resp);
			} catch (Exception e) {
				System.out.println("/likeAdd.bo : "+e);
			}
			break;
		case "/likeCancel.bo":
			try {
				new LikeCancelOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/likeCancel.bo :"+e);
			}
			break;
		case "/bookmarkAdd.bo":
			try {
				new BookmarkAddOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/bookmarkAdd.bo :"+e);
			}
			break;
		case "/bookmarkCancel.bo":
			try {
				new BookmarkCancelOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/bookmarkCancel.bo :"+e);
			}
			break;
		case "/filedownload.bo":
			try {
				new FileDownloadAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/filedownload.bo : "+e);
			}
			break;
		}
		

		if(transfer != null) {
			if(transfer.isRedirect()) {
				resp.sendRedirect(transfer.getPath());
			}
			else {
				req.getRequestDispatcher(transfer.getPath()).forward(req, resp);
			}
		}
	}
}