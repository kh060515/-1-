package com.kky.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.JoinMembershipDAO;
import com.domain.JoinMembershipDTO;
import com.utils.CommandAction;

public class PwFindCheckCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pw=request.getParameter("pw");
		String authenticationNumber=request.getParameter("checknumber");
		
		JoinMembershipDAO dao=new JoinMembershipDAO();
	
		JoinMembershipDTO dto=dao.numberCheck(authenticationNumber);
		request.setAttribute("dto", dto);
		return new CommandAction(false, "pwCheckandLogin.jsp");
	}
}
