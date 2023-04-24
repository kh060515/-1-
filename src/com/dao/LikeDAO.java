package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.domain.myBoardDTO;

import com.domain.likeBoardDTO;


public class LikeDAO {

	private DataSource dataFactory;

	public LikeDAO() {
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
	
	public int choice(String mb_id, int num) {
		int like=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM t_like_board where mb_id=? and bo_num=?";
		ResultSet rs = null;
		
		try {
			
			conn = dataFactory.getConnection();
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, mb_id);
			pstmt.setInt(2, num);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				
				like = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return like;
	}

	public void likeDelete(int num, String mb_id) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		String sql = "delete t_like_board where bo_num=? and mb_id=?";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, num);
			pstmt.setString(2, mb_id);
			
			pstmt.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null);
		}
	}

	public List<myBoardDTO> like(String login_id) {
		List<myBoardDTO> like = new ArrayList<myBoardDTO>();
		Connection conn=null;
		PreparedStatement pstmt = null;
		String sql="select * from \r\n" + 
				"(select rownum as rnum, num, mb_id, title, content, orgfilename, filename, login_read, del_flag, to_char(writerday, 'yyyy-mm-dd') writerday, readcnt, repRoot, repStep, repIndent from\r\n" + 
				"(select total.* from t_total_board total, t_like_board likeb where total.num = likeb.bo_num and likeb.mb_id = ?))";
		
		ResultSet rs=null;
		try {
			conn=dataFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, login_id);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				int num = rs.getInt("num");
				String mb_id = rs.getString("mb_id");
				String title = rs.getString("title");
				String writerday = rs.getString("writerday");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				
				myBoardDTO dto = new myBoardDTO(num, mb_id, title, null, writerday, null, null, null, readcnt, repRoot, repStep, repIndent, null);
				like.add(dto);	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}		
		return like;
	}
	
	public void likeInsert(likeBoardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into t_like_board (num,bo_num, mb_id) values (?,?,?)";

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			int num=createLikeNum(conn);
			
			pstmt.setInt(1, num);
			pstmt.setInt(2, dto.getBo_num());
			pstmt.setString(3, dto.getMb_id());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}

	}

	private int createLikeNum(Connection conn) {
		int num = 1;
		PreparedStatement pstmt = null;
		String sql = "select max(num) from t_like_board";
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, rs);
		}

		return num;
	}

}
