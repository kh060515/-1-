/*package com.kky.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import javax.xml.ws.RequestWrapper;

import com.dao.JoinMembershipDAO;
import com.utils.CommandAction;

public class idcheckCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid=request.getParameter("userid");
		JoinMembershipDAO dao=new JoinMembershipDAO();
		int cnt=dao.idCheck(userid);
		request.setAttribute("cnt", cnt);
		return new CommandAction(false, "JoinMembershop.jsp");
	}

}
*/