package com.kky.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.JoinMembershipDAO;
import com.domain.JoinMembershipDTO;
import com.utils.CommandAction;

public class PIMUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name=request.getParameter("name");
		String juminNumber=request.getParameter("juminNumber");
		
		JoinMembershipDAO dao=new JoinMembershipDAO();
		JoinMembershipDTO dto=new JoinMembershipDTO();
		dto=dao.PIMUI(new JoinMembershipDTO(null, null, name, juminNumber, null, null, null,null));
	
		request.setAttribute("dto", dto);
		return new CommandAction(false,"PIM.jsp");
	}

}
