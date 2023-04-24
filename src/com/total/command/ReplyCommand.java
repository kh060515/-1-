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

public class ReplyCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sOrgNum = request.getParameter("orgnum");
		int orgNum = -1;
		if(sOrgNum != null) {
			orgNum = Integer.valueOf(sOrgNum);
		}
		String sOrgRoot = request.getParameter("orgroot");
		int orgRoot = -1;
		if(sOrgRoot != null) {
			orgRoot = Integer.valueOf(sOrgRoot);
		}
		String sOrgStep = request.getParameter("orgstep");
		int orgStep = -1;
		if(sOrgStep != null) {
			orgStep = Integer.valueOf(sOrgStep);
		}
		String sOrgIndent = request.getParameter("orgindent");
		int orgIndent = -1;
		if(sOrgIndent != null) {
			orgIndent = Integer.valueOf(sOrgIndent);
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String orgfilename = "";		//파일업로드 orgfilename
		String filename = "";		//filename
		String login_read = request.getParameter("login_read");

		HttpSession session = request.getSession();
		JoinMembershipDTO login = (JoinMembershipDTO)session.getAttribute("login");
		String mb_id = login.getId();
		
		myBoardDAO dao = new myBoardDAO();
		//dao.reply(new myBoardDTO(orgNum, null, null, null, null, 0, orgRoot, orgStep, orgIndent), new myBoardDTO(-1, writer, title, content, null, -1, -1, -1, -1));
		dao.reply(new myBoardDTO(orgNum, null, null, null, null, null, null, null, 0, orgRoot, orgStep, orgIndent, null), new myBoardDTO(-1, mb_id, title, content, null, orgfilename, filename, login_read, -1, -1, -1, -1, null));
		
		
		return new CommandAction(true, "listpage.bo");
	}

}
