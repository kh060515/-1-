package com.total.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.myBoardDAO;
import com.domain.JoinMembershipDTO;
import com.domain.PageTo;
import com.utils.CommandAction;

public class ListPageCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int curPage = 1;
		
		String sCurPage = request.getParameter("curPage");
		if(sCurPage != null) {
			curPage = Integer.valueOf(sCurPage);
		}
		
		JoinMembershipDTO loginid = (JoinMembershipDTO)request.getSession().getAttribute("login");
		String id = null;
		if(loginid != null) {
			id = loginid.getId();
		}
		
		myBoardDAO dao = new myBoardDAO();
		PageTo to = dao.listPage(curPage, id);
		
		request.setAttribute("to", to);
		request.setAttribute("list", to.getList());
		
		HttpSession session = request.getSession(false);
		JoinMembershipDTO login = (JoinMembershipDTO)session.getAttribute("login");
		request.setAttribute("login", login);
				
				
		return new CommandAction(false, "/board/listpage.jsp");
	}

}
