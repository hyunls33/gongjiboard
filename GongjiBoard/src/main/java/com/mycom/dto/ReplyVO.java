package com.mycom.dto;

public class ReplyVO {
	private Integer rno;        //´ñ±Û ¹øÈ£
    private Integer id;         //°Ô½Ã±Û ¹øÈ£
    private String replytext;   //´ñ±Û ³»¿ë
    private String replyer;     //´ñ±Û ÀÛ¼ºÀÚ
    private String regdate;     //´ñ±Û ÀÛ¼ºÀÏÀÚ
    
    // Getter/Setter
    public Integer getRno() {
        return rno;
    }
    public void setRno(Integer rno) {
        this.rno = rno;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getReplytext() {
        return replytext;
    }
    public void setReplytext(String replytext) {
        this.replytext = replytext;
    }
    public String getReplyer() {
        return replyer;
    }
    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }
    public String getRegdate() {
        return regdate;
    }
    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

}
