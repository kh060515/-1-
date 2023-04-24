package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */
@WebServlet("/download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName=request.getParameter("fileName");
		String uploadPath="c:"+File.separator+"upload";
		
		String filePath=uploadPath+File.separator+fileName;
		
		String sMimeType=getServletContext().getMimeType(filePath);
		
		if (sMimeType==null) {
			sMimeType="application/octet-stream";
		}
		
		response.setContentType(sMimeType);
		
		String encoding=new String(fileName.getBytes("euc-kr"), "8859_1");
		
		response.setHeader("Content-Disposition","attachment;filename="+encoding);
		
		InputStream in = new FileInputStream(filePath);
		
		ServletOutputStream out= response.getOutputStream();
		
		byte[] arr = new byte[1024];
		int length;
		
		while (true) {
			length=in.read(arr,0,arr.length);
			
			if (length==-1) {
				break;
			}
			out.write(arr,0,length);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
