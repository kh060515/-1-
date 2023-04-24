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
		
		//������������ �ٲ�� ���۹�ȣ�� ����ȣ�� �ٲ�ϴ�.
		startNum = (curPage-1) * perPage + 1;
		endNum = curPage*perPage;
		if(endNum > amount){
			endNum = amount;
		}
		
		//sql�� ���� num��
		beginPageNum = (((curPage-1)/10)*10) +1;
		
		//sql�� ��num��
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
		//������ �� �ҷ��� �Խñ� ������ �����ϸ�
		//�� ������ ������ �޶����� ������ 
		//�Ʒ��� �� ������ ������ �������ش�.
		totalPage = (amount - 1) / perPage + 1;
		
		//�Խñ� ������ �ٲ�� ���۹�ȣ�� ����ȣ�� �ٲ�ϴ�.
		startNum = (curPage-1) * perPage + 1;
		endNum = curPage*perPage;
		if(endNum > amount){
			endNum = amount;
		}
		
		//totalPage���� �ٲ�� ���̱� ������
		//sql�� ��num���� �־�����Ѵ�.
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
		
		//startNum�� �ȳִ� ������ startNum�� ���Ŀ��� amount�� ������ ���⶧���� ���Ծ���.
		endNum = curPage*perPage;
		if(endNum > amount){
			endNum = amount;
		}
		
		//totalPage���� �ٲ�� ���̱� ������
		//sql�� ��num���� �־�����Ѵ�.
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
		//������������ �ٲ�� ���۹�ȣ�� ����ȣ�� �ٲ�ϴ�.
		startNum = (curPage-1) * perPage + 1;
		endNum = curPage*perPage;
		if(endNum > amount){
			endNum = amount;
		}
		
		//sql�� ���� num��
		beginPageNum = (((curPage-1)/10)*10) +1;
		//sql�� ��num��
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

		//totalPage���� �ٲ�� ���̱� ������
		//sql�� ��num���� �־�����Ѵ�.
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
