package com.mycom.dto;

public class GongjiVO {
	private String id;        //�Խñ� ��ȣ
    private String title;     //�Խñ� ����
    private String date;      //�Խñ� �ۼ� ��¥
    private String content;   //�Խñ� ����
    private int viewcnt;      //�Խñ� ��ȸ �� 
    private int recnt;        //�Խñ� ����� ��
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public int getRecnt() {
		return recnt;
	}
	public void setRecnt(int recnt) {
		this.recnt = recnt;
	}
}
