package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.total.command.Command;
import com.total.command.DeleteCommand;
import com.total.command.ListCommand;
import com.total.command.ListPageCommand;
import com.total.command.ReadCommand;
import com.total.command.ReplyCommand;
import com.total.command.ReplyUICommand;
import com.total.command.UpdateCommand;
import com.total.command.UpdateUICommand;
import com.total.command.WriteCommand;
import com.total.command.WriteUICommand;
import com.total.command.Command;
import com.utils.CommandAction;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.bo")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String ctxp = request.getContextPath();
		String sp = uri.substring(ctxp.length());
		
		Command com = null;
		if(sp.equalsIgnoreCase("/listpage.bo")) {
			com = new ListPageCommand();	
		} else if(sp.equalsIgnoreCase("/list.bo")) {
			com = new ListCommand();			
		} else if(sp.equalsIgnoreCase("/writeui.bo")) {
			com = new WriteUICommand();			
		} else if(sp.equalsIgnoreCase("/write.bo")) {
			com = new WriteCommand();			
		} else if(sp.equalsIgnoreCase("/read.bo")) {
			com = new ReadCommand();			
		} else if(sp.equalsIgnoreCase("/delete.bo")) {
			com = new DeleteCommand();			
		} else if(sp.equalsIgnoreCase("/updateui.bo")) {
			com = new UpdateUICommand();			
		} else if(sp.equalsIgnoreCase("/update.bo")) {
			com = new UpdateCommand();			
		} else if(sp.equalsIgnoreCase("/replyui.bo")) {
			com = new ReplyUICommand();			
		} else if(sp.equalsIgnoreCase("/reply.bo")) {
			com = new ReplyCommand();			
		} 
		
		if(com != null) {
			CommandAction action = com.execute(request, response);
			
			if(action.isRedirect()) {
				response.sendRedirect(action.getWhere());
			} else {
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
