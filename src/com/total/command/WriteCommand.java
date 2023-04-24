package com.total.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.myBoardDAO;
import com.domain.JoinMembershipDTO;
import com.domain.myBoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.utils.CommandAction;

public class WriteCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String title="";
		String content="";
		
		String orgFileName="";
		String fileName="";
		String login_read = "";
		

		MultipartRequest multi= new MultipartRequest(request, "c:"+File.separator+"upload", 1024*1024*10, "euc-kr", new DefaultFileRenamePolicy());
		
		title=multi.getParameter("title");
		content=multi.getParameter("content");
		orgFileName=multi.getOriginalFileName("file");
		fileName=multi.getFilesystemName("file");
		login_read = multi.getParameter("login_read");
		
		if(login_read == null) {
			login_read = "N";
		} else if(login_read.equals("on")) {
			login_read = "Y";
		} else {
			login_read = "N";
		}
		
		HttpSession session = request.getSession(false);
		JoinMembershipDTO login = (JoinMembershipDTO)session.getAttribute("login");
		String mb_id = login.getId();
				
		myBoardDAO dao = new myBoardDAO();
		dao.write(new myBoardDTO(-1, mb_id, title, content, null, orgFileName, fileName, login_read, -1, -1, -1, -1, null));
		
				
		return new CommandAction(false, "listpage.bo");
	}

}
