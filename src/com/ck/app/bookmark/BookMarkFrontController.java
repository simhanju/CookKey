package com.ck.app.bookmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Transfer;


public class BookMarkFrontController extends HttpServlet {
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
		//길 나누는 코드
		String requestURI = req.getRequestURI();// ???/userjoin.us
		String contextPath = req.getContextPath(); // ???
		String command = requestURI.substring(contextPath.length());
		
		System.out.println(command);

		Transfer transfer = null;
		switch(command) {
		case "/bookmarklist.bm":
			try {
				new BookMarkListOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/bookmarklist.bm : "+e);
			}
			break;
		
		case "/bookmarkclick.bm":
			try {
				new BookMarkClickOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/bookmarkclick.bm : "+e);
			}
			break;
			
		case "/bookmarkcancel.bm":
			try {
				new BookMarkCancelOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/bookmarkcancel.bm : "+e);
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
