package com.total.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.myBoardDAO;
import com.domain.myBoardDTO;
import com.utils.CommandAction;

public class UpdateUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = -1;
		if(sNum != null) {
			num = Integer.valueOf(sNum);
			
			myBoardDAO dao = new myBoardDAO();
			myBoardDTO dto = dao.updateui(num);
			
			request.setAttribute("dto", dto);
			
			return new CommandAction(false, "/board/update.jsp");			
		} else {
			return new CommandAction(true, "/board/error.jsp");
		}
	}

}
