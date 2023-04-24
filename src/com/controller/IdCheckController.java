package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.dao.JoinMembershipDAO;


/**
 * Servlet implementation class IdCheckController
 */
@WebServlet("*.ck")
public class IdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		//System.out.println(id);
		
		JoinMembershipDAO dao = new JoinMembershipDAO();
		int cnt = dao.idcheck(id);
		  
		// return type¿∫ json¿∏∑Œ
		JSONObject obj = new JSONObject();
		obj.put("cnt", cnt);
		  
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(obj);
	}

}
