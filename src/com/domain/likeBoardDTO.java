package com.domain;

import java.io.Serializable;

public class likeBoardDTO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int num;
	private int bo_num;
	private String mb_id;
	
	public likeBoardDTO() {
		// TODO Auto-generated constructor stub
	}

	
	public likeBoardDTO(int num, int bo_num, String mb_id) {
		super();
		this.num = num;
		this.bo_num = bo_num;
		this.mb_id = mb_id;
	}

	
	

	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public int getBo_num() {
		return bo_num;
	}


	public void setBo_num(int bo_num) {
		this.bo_num = bo_num;
	}


	public String getMb_id() {
		return mb_id;
	}


	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mb_id == null) ? 0 : mb_id.hashCode());
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
		likeBoardDTO other = (likeBoardDTO) obj;
		if (mb_id == null) {
			if (other.mb_id != null)
				return false;
		} else if (!mb_id.equals(other.mb_id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "likeBoardDTO [num=" + num + ", bo_num=" + bo_num + ", mb_id=" + mb_id + "]";
	}
	
	
	
	
}
