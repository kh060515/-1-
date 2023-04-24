package com.kky.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.JoinMembershipDAO;
import com.domain.JoinMembershipDTO;
import com.utils.CommandAction;

public class PwFindCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		String juminNumber=request.getParameter("juminNumber");
		String email=request.getParameter("email");
		
		JoinMembershipDAO dao=new JoinMembershipDAO();
		JoinMembershipDTO dto=new JoinMembershipDTO(id,pw, name, juminNumber, null, email, null,null);
		dao.PwFind(dto);
		return new CommandAction(true, "PwFindCheck.jsp");
	}

}
