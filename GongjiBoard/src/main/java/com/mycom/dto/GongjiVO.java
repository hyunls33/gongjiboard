package com.mycom.dto;

public class GongjiVO {
	private String id;        //게시글 번호
    private String title;     //게시글 제목
    private String date;      //게시글 작성 날짜
    private String content;   //게시글 내용
    private int viewcnt;      //게시글 조회 수 
    private int recnt;        //게시글 댓글의 수
    
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
