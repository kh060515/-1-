package com.like.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LikeDAO;
import com.utils.CommandAction;

public class likeDeleteCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num=0;
		if (sNum != null) {
			num=Integer.valueOf(sNum);
		}
		
		String mb_id=request.getParameter("mb_id");
		
		LikeDAO dao =new LikeDAO();
		dao.likeDelete(num,mb_id);
		
		
		return new CommandAction(true, "read.bo?num="+num);
	}

}
