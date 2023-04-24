package com.total.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LikeDAO;
import com.dao.myBoardDAO;
import com.domain.JoinMembershipDTO;
import com.domain.myBoardDTO;
import com.utils.CommandAction;

public class ReadCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		JoinMembershipDTO loginid = (JoinMembershipDTO)request.getSession().getAttribute("login");
		String id = null;
		if(loginid != null) {
			id = loginid.getId();
		}
		
		String sNum = request.getParameter("num");
		int num = -1;
		if(sNum != null) {
			num = Integer.valueOf(sNum);
			
			myBoardDAO dao = new myBoardDAO();
			myBoardDTO dto = dao.read(num, id);
			
			request.setAttribute("dto", dto);
			
			LikeDAO likeDao = new LikeDAO();
			
			int choice=likeDao.choice(id, num);
			request.setAttribute("choice", choice);
			
			
			return new CommandAction(false, "/board/read.jsp");
		} else {
			return new CommandAction(true, "/board/error.jsp");
		}
	}

}
