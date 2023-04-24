package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kky.command.AccountDeleteCommand;
import com.kky.command.Command;
import com.kky.command.JoinMembershipCommand;
import com.kky.command.JoinMembershipUICommand;
import com.kky.command.LoginActionCommand;
import com.kky.command.LogoutCommand;
import com.kky.command.PIMCommand;
import com.kky.command.PIMUICommand;
import com.kky.command.PwFindCheckCommand;
import com.kky.command.PwFindCommand;
import com.kky.command.PwFindUICommand;
import com.utils.CommandAction;

/**
 * Servlet implementation class JoinMembershipController
 */
@WebServlet("*.do")
public class JoinMembershipController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinMembershipController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		String ctxp=request.getContextPath();
		String sp=uri.substring(ctxp.length());
		Command com=null;
		
		if (sp.equalsIgnoreCase("/JoinMembership.do")) {
			com=new JoinMembershipCommand();
		}else if (sp.equalsIgnoreCase("/JoinMembershipUI.do")) {
			com=new JoinMembershipUICommand();
		}else if (sp.equalsIgnoreCase("/PwFindUI.do")) {
			com=new PwFindUICommand();
		}else if (sp.equalsIgnoreCase("/PwFind.do")) {
			com=new PwFindCommand();
		}else if (sp.equalsIgnoreCase("/PwFindCheck.do")) {
			com=new PwFindCheckCommand();
		}else if (sp.equalsIgnoreCase("/loginaction.do")) {
			com=new LoginActionCommand();
		}else if (sp.equalsIgnoreCase("/PIMUI.do")) {
			com=new PIMUICommand();
		}else if (sp.equalsIgnoreCase("/PIM.do")) {
			com=new PIMCommand();
		}else if (sp.equalsIgnoreCase("/accountDelete.do")) {
			com=new AccountDeleteCommand();
		}else if (sp.equalsIgnoreCase("/logout.do")) {
			com=new LogoutCommand();
		}
		/*else if (sp.equalsIgnoreCase("/idcheck.do")) {
			com=new idcheckCommand();
		}*/
		
		if (com!=null) {
			CommandAction action=com.execute(request, response);
			if (action.isRedirect()) {
				response.sendRedirect(action.getWhere());
			}else {
				request.getRequestDispatcher(action.getWhere()).forward(request, response);
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
