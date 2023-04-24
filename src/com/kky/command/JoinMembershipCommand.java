package com.kky.command;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.JoinMembershipDAO;
import com.domain.JoinMembershipDTO;
import com.sun.xml.internal.ws.client.sei.ValueSetter;
import com.utils.CommandAction;

public class JoinMembershipCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		String juminNumber=request.getParameter("juminNumber");
		String trans=request.getParameter("trans");
		String email=request.getParameter("email");
		/*String trans=Arrays.toString(sTrans);
		trans=trans.substring(1, trans.length()-1);*/
		
		
		
		JoinMembershipDAO dao=new JoinMembershipDAO();
		JoinMembershipDTO dto=new JoinMembershipDTO(id, pw, name, juminNumber, trans, email, null,null);
		dao.JoinMember(dto);
		
		return new CommandAction(true, "index.jsp");
	}

}
