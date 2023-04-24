package com.total.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.myBoardDAO;
import com.domain.myBoardDTO;
import com.utils.CommandAction;

public class ListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		myBoardDAO dao = new myBoardDAO();
		List<myBoardDTO> list = dao.list();
		
		request.setAttribute("list", list);
		
		return new CommandAction(false, "/board/list.jsp");
	}

}
