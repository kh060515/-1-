package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.domain.JoinMembershipDTO;
import com.domain.myBoardDTO;

public class PopDAO {

	private DataSource dataFactory;

	public PopDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null) {
				conn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public List<myBoardDTO> pop() {
	      List<myBoardDTO> pop = new ArrayList<myBoardDTO>();
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = "select num, mb_id, title, content, orgfilename, filename, login_read, del_flag, to_char(writerday, 'yyyy-mm-dd') writerday, readcnt, repRoot, repStep, repIndent from t_total_board where num in (select distinct bo_num from t_like_board)";
	      try {
	         conn = dataFactory.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            int num = rs.getInt("num");
	            String mb_id =rs.getString("mb_id");
	            String title = rs.getString("title");
	            String content = rs.getString("content");
	            String writerday = rs.getString("writerday");
	            int readcnt = rs.getInt("readcnt");
	            int repRoot = rs.getInt("repRoot");
	            int repStep = rs.getInt("repStep");
	            int repIndent = rs.getInt("repIndent");
	            String orgfilename = rs.getString("orgfilename");
	            String filename = rs.getString("filename");
	            String login_read = rs.getString("login_read");
	            String del_flag = rs.getString("del_flag");

	            pop.add(new myBoardDTO(num, mb_id, title, content, writerday, orgfilename, filename, login_read, readcnt, repRoot, repStep, repIndent, del_flag));	            
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         closeAll(conn, pstmt, rs);
	      }
	      return pop;
	   }
	
	public void rank() {
		Connection conn =null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs= null;
		try {
			conn=dataFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, rs);
		}
	}
	
}
