package com.ck.app.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Transfer;


public class ChatFrontController extends HttpServlet {
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
		case "/chatstart.ch":
			try {
				transfer = new ChatCreateOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/chatstart.ch : "+e);
			}
			break;
		case "/chatlist.ch":
			try {
				transfer = new ChatListOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/chatlist.ch : "+e);
			}
			break;
		case "/chatview.ch":
			try {
				transfer = new ChatViewOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/chatview.ch : "+e);
			}
			break;
		case"/chatsend.ch":
			try {
				transfer = new ChatSendOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/chatsend.ch : "+e);
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