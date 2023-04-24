package com.total.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.myBoardDAO;
import com.domain.JoinMembershipDTO;
import com.domain.myBoardDTO;
import com.utils.CommandAction;

public class UpdateCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		JoinMembershipDTO login = (JoinMembershipDTO)session.getAttribute("login");
		String mb_id = login.getId();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String sNum = request.getParameter("num");
		String orgfilename = ""; 	//orgfilename
		String filename = ""; 		//filename
		
		String login_read = request.getParameter("login_read");
		if(login_read == null) {
			login_read = "N";
		} else if(login_read.equals("on")) {
			login_read = "Y";
		} else {
			login_read = "N";
		}
		
		int num = -1;
		if(sNum != null) {
			num = Integer.valueOf(sNum);
			
			myBoardDAO dao = new myBoardDAO();
			dao.update(new myBoardDTO(num, mb_id, title, content, null, orgfilename, filename, login_read, -1, -1, -1, -1, null));
								
			//return new CommandAction(false, "read.bo?num="+num);
			return new CommandAction(false, "listpage.bo");
					
		}
		
		return new CommandAction(true, "/board/error.jsp");
	}

}
