package com.kky.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.JoinMembershipDAO;
import com.domain.JoinMembershipDTO;
import com.utils.CommandAction;

public class PIMCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String juminNumber=request.getParameter("juminNumber");
		String trans=request.getParameter("trans");
		String email=request.getParameter("email");
		
		JoinMembershipDAO dao=new JoinMembershipDAO();
		JoinMembershipDTO dto=new JoinMembershipDTO(id, null, name, juminNumber, trans, email, null,null);
		dao.PIM(dto);
		return new CommandAction(true, "mainpage.jsp");
	}

}
