package com.mycom.dto;

public class ReplyVO {
	private Integer rno;        //��� ��ȣ
    private Integer id;         //�Խñ� ��ȣ
    private String replytext;   //��� ����
    private String replyer;     //��� �ۼ���
    private String regdate;     //��� �ۼ�����
    
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
