package com.domain;

import java.util.List;

public class PageTo {
	private int perPage = 10;
	private int amount;
	private int curPage;
	private int totalPage;
	private List<myBoardDTO> list;
	private int startNum;
	private int endNum;
	private int beginPageNum;
	private int stopPageNum;

	public PageTo() {
		// TODO Auto-generated constructor stub
	}

	public PageTo(int curPage) {
		super();
		this.curPage = curPage;
		
		//현재페이지가 바뀌면 시작번호와 끝번호가 바뀝니다.
		startNum = (curPage-1) * perPage + 1;
		endNum = curPage*perPage;
		if(endNum > amount){
			endNum = amount;
		}
		
		//sql문 시작 num값
		beginPageNum = (((curPage-1)/10)*10) +1;
		
		//sql문 끝num값
		stopPageNum = (((curPage-1)/10)+1)*10;
		if(stopPageNum > totalPage) {
			stopPageNum = totalPage;
		}
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
		//페이지 당 불러낼 게시글 개수를 설정하면
		//총 페이지 개수가 달라지기 때문에 
		//아래에 총 페이지 개수를 설정해준다.
		totalPage = (amount - 1) / perPage + 1;
		
		//게시글 개수가 바뀌면 시작번호와 끝번호가 바뀝니다.
		startNum = (curPage-1) * perPage + 1;
		endNum = curPage*perPage;
		if(endNum > amount){
			endNum = amount;
		}
		
		//totalPage값이 바뀌는 곳이기 때문에
		//sql문 끝num값을 넣어줘야한다.
		stopPageNum = (((curPage-1)/10)+1)*10;
		if(stopPageNum > totalPage) {
			stopPageNum = totalPage;
		}
		
	}
		
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		totalPage = (amount - 1) / perPage + 1;
		
		//startNum을 안넣는 이유는 startNum의 공식에서 amount와 관련이 없기때문에 삽입안함.
		endNum = curPage*perPage;
		if(endNum > amount){
			endNum = amount;
		}
		
		//totalPage값이 바뀌는 곳이기 때문에
		//sql문 끝num값을 넣어줘야한다.
		stopPageNum = (((curPage-1)/10)+1)*10;
		if(stopPageNum > totalPage) {
			stopPageNum = totalPage;
		}
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
		//현재페이지가 바뀌면 시작번호와 끝번호가 바뀝니다.
		startNum = (curPage-1) * perPage + 1;
		endNum = curPage*perPage;
		if(endNum > amount){
			endNum = amount;
		}
		
		//sql문 시작 num값
		beginPageNum = (((curPage-1)/10)*10) +1;
		//sql문 끝num값
		stopPageNum = (((curPage-1)/10)+1)*10;
		if(stopPageNum > totalPage) {
			stopPageNum = totalPage;
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;

		//totalPage값이 바뀌는 곳이기 때문에
		//sql문 끝num값을 넣어줘야한다.
		stopPageNum = (((curPage-1)/10)+1)*10;
		if(stopPageNum > totalPage) {
			stopPageNum = totalPage;
		}
	}

	public List<myBoardDTO> getList() {
		return list;
	}

	public void setList(List<myBoardDTO> list) {
		this.list = list;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public int getBeginPageNum() {
		return beginPageNum;
	}

	public void setBeginPageNum(int beginPageNum) {
		this.beginPageNum = beginPageNum;
	}

	public int getStopPageNum() {
		return stopPageNum;
	}

	public void setStopPageNum(int stopPageNum) {
		this.stopPageNum = stopPageNum;
	}

	@Override
	public String toString() {
		return "PageTo [perPage=" + perPage + ", amount=" + amount + ", curPage=" + curPage + ", totalPage=" + totalPage
				+ ", list=" + list + ", startNum=" + startNum + ", endNum=" + endNum + ", beginPageNum=" + beginPageNum
				+ ", stopPageNum=" + stopPageNum + "]";
	}
	
	
}
