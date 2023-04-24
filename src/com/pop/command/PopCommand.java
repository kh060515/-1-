package com.pop.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PopDAO;
import com.domain.myBoardDTO;
import com.utils.CommandAction;

public class PopCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PopDAO dao= new PopDAO();
		List<myBoardDTO>pop=dao.pop();

		
		request.setAttribute("pop", pop);
		
		return new CommandAction(false, "/pop/pop.jsp");
	}

}
