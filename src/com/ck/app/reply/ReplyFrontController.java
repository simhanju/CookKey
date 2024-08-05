package com.ck.app.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Transfer;


public class ReplyFrontController extends HttpServlet {
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
		case "/replywrite.rp":
			try {
				new ReplyWriteOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/replywrite.rp : "+e);
			}
			break;
		case "/replydelete.rp":
			try {
				new ReplyDeleteOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/replydelete.rp : "+e);
			}
			break;
		case "/replyupdate.rp":
			try {
				new ReplyUpdateOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/replyupdate.rp : "+e);
			}
			break;
		case "/replylist.rp":
	         try {
	            new ReplyListOkAction().execute(req,resp);
	         } catch (Exception e) {
	            System.out.println("replylist.rp : "+e);
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