package com.mycom.dao;

import java.util.List;

import com.mycom.dto.Criteria;
import com.mycom.dto.ReplyPageVO;
import com.mycom.dto.ReplyVO;

public interface ReplyDAO {
	//��� ���
    //public List<ReplyVO> list(int id, int start) throws Exception;
	public List<ReplyVO> list(int id) throws Exception;
    //��� �Է�
    public void create(ReplyVO vo) throws Exception;
    //��� ����
    public void update(ReplyVO vo) throws Exception;
    //��� ����
    public void delete(int rno) throws Exception;
    //���� �Խù��� ��ü ��� �� ��ȸ
	public int count(int id) throws Exception;
	
	public List<ReplyVO> listPage(int id, ReplyPageVO vo) throws Exception;
}
