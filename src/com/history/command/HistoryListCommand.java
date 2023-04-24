package com.history.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.HistoryDAO;
import com.domain.JoinMembershipDTO;
import com.domain.myBoardDTO;
import com.utils.CommandAction;

public class HistoryListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		JoinMembershipDTO loginid = (JoinMembershipDTO)request.getSession().getAttribute("login");
		String id = null;
		if(loginid != null) {
			id = loginid.getId();
		}
		
		HistoryDAO dao = new HistoryDAO();
		List<myBoardDTO> list = dao.list(id);
		
		request.setAttribute("list", list);
		
		
		HttpSession session = request.getSession(false);
		JoinMembershipDTO login = (JoinMembershipDTO)session.getAttribute("login");
		request.setAttribute("login", login);
		
		return new CommandAction(false, "/history/historylist.jsp");
	}

}
