package com.domain;

import java.io.Serializable;

public class JoinMembershipDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String pw;
	private String name;
	private String juminNumber;
	private String trans;
	private String email;
	private String AuthenticationNumber;
	private String delFlag;
	public JoinMembershipDTO() {
		// TODO Auto-generated constructor stub
	}
	public JoinMembershipDTO(String id, String pw, String name, String juminNumber, String trans, String email,
			String authenticationNumber, String delFlag) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.juminNumber = juminNumber;
		this.trans = trans;
		this.email = email;
		AuthenticationNumber = authenticationNumber;
		this.delFlag = delFlag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJuminNumber() {
		return juminNumber;
	}
	public void setJuminNumber(String juminNumber) {
		this.juminNumber = juminNumber;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthenticationNumber() {
		return AuthenticationNumber;
	}
	public void setAuthenticationNumber(String authenticationNumber) {
		AuthenticationNumber = authenticationNumber;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "JoinMembershipDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", juminNumber=" + juminNumber
				+ ", trans=" + trans + ", email=" + email + ", AuthenticationNumber=" + AuthenticationNumber
				+ ", delFlag=" + delFlag + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JoinMembershipDTO other = (JoinMembershipDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
