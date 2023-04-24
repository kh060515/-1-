package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.domain.PageTo;
import com.domain.myBoardDTO;

public class myBoardDAO {
	private DataSource dataFactory;
	
	public myBoardDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void CloseAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<myBoardDTO> list() {
		List<myBoardDTO> list = new ArrayList<myBoardDTO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select num, mb_id, title, content, readcnt, repRoot, repStep, repIndent, to_char(writerday, 'yyyy/mm/dd') as writerday from t_total_board order by repRoot desc, repStep asc";
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				myBoardDTO dto = myBoardRs(rs);
				
				list.add(dto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			CloseAll(conn, pstmt, rs);
		}
		
		return list;
	}

	public void write(myBoardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//String sql = "insert into myboard (num, writer, title, content, repRoot, repStep, repIndent) "
		//		+ "values ((select max(num)+1 from myboard), ?, ?, ?, (select max(num)+1 from myboard), 0, 0)";
		String sql = "insert into t_total_board (num, mb_id, title, content, orgfilename, filename, login_read, repRoot, repStep, repIndent) values (?, ?, ?, ?, ?, ?, ?, ?, 0, 0)";
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			int num = createNum(conn, "t_total_board");
			
			pstmt.setInt(1, num);						//create num
			pstmt.setString(2, dto.getMb_id());			//mb_id
			pstmt.setString(3, dto.getTitle());			//title
			pstmt.setString(4, dto.getContent());		//content
			pstmt.setString(5, dto.getOrgfilename());	//orgfilename
			pstmt.setString(6, dto.getFilename());		//filename
			pstmt.setString(7, dto.getLogin_read());	//login_read
			pstmt.setInt(8, num);						//repRoot :: 원글일땐 원글 num과 동일
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseAll(conn, pstmt, null);
		}
		
	}

	private int createNum(Connection conn, String board) {
		int num = 1;
		PreparedStatement pstmt = null;
		String sql = "select max(num) as num from t_total_board";
		if(board.equals("t_history_board")) {
			sql = "select max(num) as num from t_history_board";
		}
			
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt("num") + 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			CloseAll(null, pstmt, rs);
		}
		
		return num;
	}

	public myBoardDTO read(int num, String login_id) {
		myBoardDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select mb_id, title, content, orgfilename, filename, login_read, del_flag, readcnt, repRoot, repStep, repIndent, to_char(writerday, 'yyyy-mm-dd') as writerday from t_total_board where num = ?";
		ResultSet rs = null;
		boolean isOk = false;
		
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
			
			readCntIncrease(conn, num);	// 조회수
			
			//로그인 되어있을때 최근게시물 추가
			if(login_id != null) {
				int cnt = readHistorySelect(conn, num, login_id); //히스토리 내역 검색
				
				if(cnt == 0) {
					readHistoryInsert(conn, num, login_id);	//내역 없을때 insert
				} else {
					readHistoryUpdate(conn, num, login_id); //내역 있을때 날짜 update
				}				
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mb_id = rs.getString("mb_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int readcnt = rs.getInt("readcnt");
				String writerday = rs.getString("writerday");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				String orgfilename = rs.getString("orgfilename");
				String filename = rs.getString("filename");
				String login_read = rs.getString("login_read");
				String del_flag = rs.getString("del_flag");
				
				
				dto = new myBoardDTO(num, mb_id, title, content, writerday, orgfilename, filename, login_read, readcnt, repRoot, repStep, repIndent, del_flag);
				
				isOk = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(isOk) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
			CloseAll(conn, pstmt, rs);
		}
		
		return dto;
	}

	private void readHistoryInsert(Connection conn, int bo_num, String login_id) {
		PreparedStatement pstmt = null;
		String sql = "insert into t_history_board (bo_num, mb_id, num) values (?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);

			int num = createNum(conn, "t_history_board");
			
			pstmt.setInt(1, bo_num);
			pstmt.setString(2, login_id);
			pstmt.setInt(3, num);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			CloseAll(null, pstmt, null);
		}
		
	}

	private void readHistoryUpdate(Connection conn, int bo_num, String login_id) {
		PreparedStatement pstmt = null;
		String sql = "update t_history_board set day = sysdate where bo_num = ? and mb_id = ?";	
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, bo_num);
			pstmt.setString(2, login_id);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			CloseAll(null, pstmt, null);
		}
		
		
	}

	private int readHistorySelect(Connection conn, int num, String login_id) {
		int cnt = 0;
		PreparedStatement pstmt = null;
		String sql = "select count(*) as hi_cnt from t_history_board where bo_num = ? and mb_id = ?";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, login_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int hi_cnt = rs.getInt("hi_cnt");
				if(hi_cnt > 0) {
					cnt = 1;	
				}				
			}		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			CloseAll(null, pstmt, rs);
		}
		
		return cnt;
		
	}

	private void readCntIncrease(Connection conn, int num) {
		PreparedStatement pstmt = null;
		String sql = "update t_total_board set readcnt = readcnt+1 where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			CloseAll(null, pstmt, null);
		}
		
	}

	public void delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//String sql = "delete from t_total_board where num = ?";
		String sql = "update t_total_board set del_flag = 'Y' where num = ?";
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			CloseAll(conn, pstmt, null);
		}
		
	}

	public myBoardDTO updateui(int num) {
		myBoardDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select mb_id, title, content, orgfilename, filename, login_read, del_flag, readcnt, repRoot, repStep, repIndent, to_char(writerday, 'yyyy/mm/dd') as writerday from t_total_board where num = ?";
		ResultSet rs = null;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mb_id = rs.getString("mb_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int readcnt = rs.getInt("readcnt");
				String writerday = rs.getString("writerday");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				String orgfilename = rs.getString("orgfilename");
				String filename = rs.getString("filename");
				String login_read = rs.getString("login_read");
				String del_flag = rs.getString("del_flag");
				
				dto = new myBoardDTO(num, mb_id, title, content, writerday, orgfilename, filename, login_read, readcnt, repRoot, repStep, repIndent, del_flag);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			CloseAll(conn, pstmt, rs);
		}
		
		return dto;
	}

	public void update(myBoardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update t_total_board set title = ?, content = ?, login_read = ? where num = ?";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getLogin_read());
			pstmt.setInt(4, dto.getNum());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			CloseAll(conn, pstmt, null);
		}
		
		
	}

	public myBoardDTO replyui(int num) {
		myBoardDTO dto = updateui(num);
		
		return dto;
	}

	public void reply(myBoardDTO orgdto, myBoardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into t_total_board (num, mb_id, title, content, orgfilename, filename, login_read, repRoot, repStep, repIndent) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean isOk = false;
		
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
			
			increasRepStep(conn, orgdto);
			
			
			pstmt = conn.prepareStatement(sql);

			int num = createNum(conn, "t_total_board");
			pstmt.setInt(1, num);					//num
			pstmt.setString(2, dto.getMb_id());		//mb_id
			pstmt.setString(3, dto.getTitle());		//title
			pstmt.setString(4, dto.getContent());	//content
			pstmt.setString(5, dto.getOrgfilename());	//orgfilename
			pstmt.setString(6, dto.getFilename()); 	//filename
			pstmt.setString(7, dto.getLogin_read());//login_read
			pstmt.setInt(8, orgdto.getRepRoot());	//repRoot : 원글의 root 값과 동일
			pstmt.setInt(9, orgdto.getRepStep()+1);
			pstmt.setInt(10, orgdto.getRepIndent()+1);
			
			pstmt.executeUpdate();
			
			isOk = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(isOk) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			CloseAll(conn, pstmt, null);
		}
		
	}

	private void increasRepStep(Connection conn, myBoardDTO orgdto) {
		PreparedStatement pstmt = null;
		String sql = "update t_total_board set repStep=repStep + 1 where repRoot = ? and repStep > ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orgdto.getRepRoot());
			pstmt.setInt(2, orgdto.getRepStep());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			CloseAll(null, pstmt, null);
		}
		
		
	}

	public PageTo listPage(int curPage, String login_id) {
		PageTo to = new PageTo(curPage);
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "select * from \r\n" + 
					"	(select rownum as rnum, num, mb_id, title, content, orgfilename, filename, login_read, del_flag, to_char(writerday, 'yyyy-mm-dd') writerday, readcnt, repRoot, repStep, repIndent from \r\n" + 
					"		(select * from t_total_board where del_flag = 'N' order by repRoot desc, repStep asc)\r\n" + 
					"	)\r\n" + 
					"where rnum between ? and ? ";
		
		if(login_id == null) {		//로그인 안했을때
			sql = "select * from \r\n" + 
					"	(select rownum as rnum, num, mb_id, title, content, orgfilename, filename, login_read, del_flag, to_char(writerday, 'yyyy-mm-dd') writerday, readcnt, repRoot, repStep, repIndent from \r\n" + 
					"		(select * from t_total_board where del_flag = 'N' and login_read != 'Y' order by repRoot desc, repStep asc)\r\n" + 
					"	)\r\n" + 
					"where rnum between ? and ? ";
		}
		
			
		ResultSet rs = null;
		List<myBoardDTO> list = new ArrayList<myBoardDTO>();
		
		try {
			conn = dataFactory.getConnection();
			//int amount = getAmount(conn);
			pstmt = conn.prepareStatement(sql);
			
			//insert, update 등을 할때는 상관 있지만 select이기 때문데 따로 conn을 넣어줘서 작업할 필요는 없다.
			//해도 상관은 없지만 만약 구현 할거라면 conn 선언 바로 밑에서 실행해 줘야한다
			int amount = getAmount(login_id);
			to.setAmount(amount);
			
			pstmt.setInt(1, to.getStartNum());
			pstmt.setInt(2, to.getEndNum());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String mb_id = rs.getString("mb_id");
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

				
				myBoardDTO dto = new myBoardDTO(num, mb_id, title, content, writerday, orgfilename, filename, login_read, readcnt, repRoot, repStep, repIndent, del_flag);
				
				list.add(dto);
			}
			
			//list 설정
			to.setList(list);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			CloseAll(conn, pstmt, rs);
		}
		
		return to;
	}

	private int getAmount(String login_id) {
		int amount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;		
		String sql = "select count(num) from t_total_board where del_flag = 'N'";
		if(login_id == null) {		//로그인 안했을때
			sql = "select count(num) from t_total_board where del_flag = 'N' and login_read != 'Y'";
		}
		ResultSet rs = null;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				amount = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			CloseAll(conn, pstmt, rs);
		}
		
		return amount;
	}
	
	private myBoardDTO myBoardRs(ResultSet rs) {
		myBoardDTO dto = null;
		
		try {
			int num = rs.getInt("num");
			String mb_id = rs.getString("mb_id");
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
			
			dto = new myBoardDTO(num, mb_id, title, content, writerday, orgfilename, filename, login_read, readcnt, repRoot, repStep, repIndent, del_flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
		
	}
}
