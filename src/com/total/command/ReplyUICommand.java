package com.total.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.myBoardDAO;
import com.domain.myBoardDTO;
import com.utils.CommandAction;

public class ReplyUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = -1;
		if(sNum != null) {
			num = Integer.valueOf(sNum);
		}
		
		myBoardDAO dao = new myBoardDAO();
		//원글에 대한 num으로 원글의 정보를 가져와야함.
		myBoardDTO orgdto =  dao.replyui(num);
		
		request.setAttribute("orgdto", orgdto);
		
		return new CommandAction(false, "/board/reply.jsp");
	}

}
