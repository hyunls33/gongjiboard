package com.mycom.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mycom.dao.ReplyDAO;
import com.mycom.dto.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
    
    @Inject
    ReplyDAO replyDao;
    
    /*//��� ���
    @Override
    public List<ReplyVO> list(int id, int start) throws Exception {
        return replyDao.list(id, start);
    }*/
    //��� ���
    @Override
    public List<ReplyVO> list(int id) throws Exception {
        return replyDao.list(id);
    }
    
    //��� �ۼ�
    @Override
    public void create(ReplyVO vo) throws Exception {
        replyDao.create(vo);
    }
    
    //��� ����
    @Override
    public void update(ReplyVO vo) throws Exception {
        // TODO Auto-generated method stub
    	replyDao.update(vo);
    }
    
    //��� ����
    @Override
    public void delete(int rno) throws Exception {
        // TODO Auto-generated method stub
    	replyDao.delete(rno);
    }
 
    //���� �Խù��� ��ü ��� �� ��ȸ
    @Override
  	public int count(int id) throws Exception {
    	return replyDao.count(id);
    }
}
