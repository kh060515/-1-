package com.kky.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.JoinMembershipDAO;
import com.domain.JoinMembershipDTO;
import com.utils.CommandAction;

public class LoginActionCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		
		JoinMembershipDAO dao=new JoinMembershipDAO();
		JoinMembershipDTO dto=new JoinMembershipDTO(id, pw, null, null, null, null, null,null);
		
		JoinMembershipDTO login=dao.login(dto);
		if(login != null) {
			if (login.getDelFlag().equals("Y")) {
				return new CommandAction(true, "idDelete.jsp");
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("login", login);
				
				return new CommandAction(false,"index.jsp");
			}
		} else {
			request.setAttribute("err", "not");
			return new CommandAction(false, "loginform.jsp");
		}
	}

}
