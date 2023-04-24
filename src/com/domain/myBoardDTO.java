package com.domain;

import java.io.Serializable;

public class myBoardDTO implements Serializable{
/*
 * create table myboard(
	num number(4) primary key,
	writer varchar2(21) not null,
	title varchar2(45) not null,
	content varchar2(2000) not null,
	writeday date default sysdate,
	readcnt number(4) default 0,
	repRoot number(4),
	repStep number(4),
	repIndent number(4)	
* )
* 
* 	mb_id varchar2(10) references t_member(num),
	num number primary key
	title
	content
	orgfilename
	filename
	login_read varchar2(2) defult N,		:: 로그인 한사람만 볼 수 있는 글로 지정하면 Y 전체공개면 N
	readcnt
	writerday date default sysdate,
	repRoot
	repStep
	Indent
	del_flag varchar2(2) defult N

 */
	private static final long serialVersionUID = 1L;
	private int num;
	private String mb_id;
	private String title;
	private String content;
	private String writerday;
	private String orgfilename;
	private String filename;
	private String login_read;
	private int readcnt;
	private int repRoot;
	private int repStep;
	private int repIndent;
	private String del_flag;
	
	public myBoardDTO() {
		// TODO Auto-generated constructor stub
	}

	public myBoardDTO(int num, String mb_id, String title, String content, String writerday, String orgfilename,
			String filename, String login_read, int readcnt, int repRoot, int repStep, int repIndent, String del_flag) {
		super();
		this.num = num;
		this.mb_id = mb_id;
		this.title = title;
		this.content = content;
		this.writerday = writerday;
		this.orgfilename = orgfilename;
		this.filename = filename;
		this.login_read = login_read;
		this.readcnt = readcnt;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
		this.del_flag = del_flag;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriterday() {
		return writerday;
	}

	public void setWriterday(String writerday) {
		this.writerday = writerday;
	}

	public String getOrgfilename() {
		return orgfilename;
	}

	public void setOrgfilename(String orgfilename) {
		this.orgfilename = orgfilename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getLogin_read() {
		return login_read;
	}

	public void setLogin_read(String login_read) {
		this.login_read = login_read;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public int getRepRoot() {
		return repRoot;
	}

	public void setRepRoot(int repRoot) {
		this.repRoot = repRoot;
	}

	public int getRepStep() {
		return repStep;
	}

	public void setRepStep(int repStep) {
		this.repStep = repStep;
	}

	public int getRepIndent() {
		return repIndent;
	}

	public void setRepIndent(int repIndent) {
		this.repIndent = repIndent;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	@Override
	public String toString() {
		return "myBoardDTO [num=" + num + ", mb_id=" + mb_id + ", title=" + title + ", content=" + content
				+ ", writerday=" + writerday + ", orgfilename=" + orgfilename + ", filename=" + filename
				+ ", login_read=" + login_read + ", readcnt=" + readcnt + ", repRoot=" + repRoot + ", repStep="
				+ repStep + ", repIndent=" + repIndent + ", del_flag=" + del_flag + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
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
		myBoardDTO other = (myBoardDTO) obj;
		if (num != other.num)
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
