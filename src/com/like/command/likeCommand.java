package com.like.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LikeDAO;
import com.domain.JoinMembershipDTO;
import com.domain.myBoardDTO;
import com.utils.CommandAction;

public class likeCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		JoinMembershipDTO loginid = (JoinMembershipDTO)request.getSession().getAttribute("login");
		String mb_id = null;
		if(loginid != null) {
			mb_id = loginid.getId();
		}
		
		LikeDAO dao = new LikeDAO();
		List<myBoardDTO> like=dao.like(mb_id);
		
		request.setAttribute("like", like);
		
		return new CommandAction(false, "/like/like.jsp");
	}

}
