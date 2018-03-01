package com.mycom.service;

import java.util.List;
import com.mycom.dto.ReplyVO;

public interface ReplyService {
	// ��� ���
    public List<ReplyVO> list(int id, int start) throws Exception;
    // ��� �Է�
    public void create(ReplyVO vo) throws Exception;
    // ��� ����
    public void update(ReplyVO vo) throws Exception;
    // ��� ����
    public void delete(int rno) throws Exception;
    //�Խù� ������ �ش� �Խù��� ��۵� ����
    public void deleteReply(int rno) throws Exception;
    //���� �Խù��� ��ü ��� �� ��ȸ
  	public int count(int id) throws Exception;
}
