package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.history.command.Command;
import com.history.command.HistoryListCommand;
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
import com.utils.CommandAction;

/**
 * Servlet implementation class HistoryController
 */
@WebServlet("*.hi")
public class HistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryController() {
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
		if(sp.equalsIgnoreCase("/historylist.hi")) {
			com = new HistoryListCommand();	
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
