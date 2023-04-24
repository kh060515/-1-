package com.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.PStmtKey;

import com.domain.JoinMembershipDTO;
import com.function.CheckNumberGenerator;
import com.function.MailExam;
import com.sun.mail.iap.Response;
import com.sun.org.apache.xml.internal.dtm.DTMDOMException;

public class JoinMembershipDAO {
	private DataSource dataFctory;
	public JoinMembershipDAO() {
		try {
			Context ctx=new InitialContext();
			dataFctory=(DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		try {
			if (rs!=null) {
				rs.close();
			}
			if (pstmt!=null) {
				pstmt.close();
			}
			if (conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void JoinMember(JoinMembershipDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into t_member (id,pw,name,juminNumber,trans,email) values (?,?,?,?,?,?)";
		
		try {
			conn=dataFctory.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getJuminNumber());
			pstmt.setString(5, dto.getTrans());
			pstmt.setString(6, dto.getEmail());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null);
		}
	}

	public void PwFind(JoinMembershipDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select email from t_member"
				+ " where"
				+ " id=? and name=? and juminNumber=? and email=?";
		
		String sql2="update t_member set AuthenticationNumber=? where id=?";
		
		/*String sql3="select pw from t_member"
				+ " where"
				+ " AuthenticationNumber=?";*/
		String email = null;
		String sentence=null;
		CheckNumberGenerator chg=new CheckNumberGenerator();
		try {
			conn=dataFctory.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getJuminNumber());
			pstmt.setString(4, dto.getEmail());
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				email=rs.getString("email");
			}
			closeAll(conn, pstmt, rs);
			
			sentence=chg.checknumber();
			
			conn=dataFctory.getConnection();
			pstmt=conn.prepareStatement(sql2);
			pstmt.setString(1, sentence);
			pstmt.setString(2, dto.getId());
			pstmt.executeUpdate();
			//
			MailExam.main(email,sentence);
	//
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, rs);
			
		}
	}

	public JoinMembershipDTO numberCheck(String authenticationNumber) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select pw from t_member where AuthenticationNumber=?";
		JoinMembershipDTO dto=new JoinMembershipDTO();
		String pw=null;
		try {
			conn=dataFctory.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, authenticationNumber);
			
			rs=pstmt.executeQuery();
			if (rs.next()) {
				pw=rs.getString("pw");
				
				System.out.println("당신의 비밀번호는"+pw+"입니다.");
				dto=new JoinMembershipDTO(null, pw, null, null, null, null, null,null);
			}else {
				System.out.println("error");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null);
		}
		return dto;
	}

	public JoinMembershipDTO login(JoinMembershipDTO dto) {
		JoinMembershipDTO login=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select del_flag,id from t_member where id=? and pw=?";
		try {
			conn=dataFctory.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			rs=pstmt.executeQuery();
			while (rs.next()) {
				String delFlag=rs.getString("del_flag");
				String id=rs.getString("id");
				login=new JoinMembershipDTO(id, null, null, null, null, null, null, delFlag);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, rs);
		}
		return login;
	}

	public JoinMembershipDTO PIMUI(JoinMembershipDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select id,name,juminNumber,trans,email from t_member"
				+ " where"
				+ " name=? and juminNumber=?";
		
		try {
			conn=dataFctory.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,dto.getName());
			pstmt.setString(2,dto.getJuminNumber());
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				String id=rs.getString("id");
				String name=rs.getString("name");
				String juminNumber=rs.getString("juminNumber");
				String trans=rs.getString("trans");
				String email=rs.getString("email");
				dto=new JoinMembershipDTO(id, null, name, juminNumber, trans, email, null,null);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, rs);
		}
		return dto;
	}

	public void PIM(JoinMembershipDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update t_member set name=?,juminNumber=?,trans=?,email=? where id=?";
		try {
			conn=dataFctory.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getJuminNumber());
			pstmt.setString(3, dto.getTrans());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getId());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null);
		}
	}

	public void accountdelete(JoinMembershipDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update t_member set del_flag='Y' where id=? and pw=? and name=? and juminNumber=?";
		try {
			conn=dataFctory.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getJuminNumber());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null);
		}
	}

	public int idcheck(String id) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select count(*) as cnt from t_member where id = ?";
		ResultSet rs = null;
		
		try {
			conn = dataFctory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		
		return cnt;
	}

}
