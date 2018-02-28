package com.mycom.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import com.mycom.dto.Criteria;

import com.mycom.dao.ReplyDAO;
import com.mycom.dto.ReplyVO;
import com.mycom.dto.ReplyPageVO;

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
    	String replyer = vo.getReplyer();
    	String replytext = vo.getReplytext();
    	replyer = replyer.replace("<", "&lt;");
    	replyer = replyer.replace(">", "&gt;");
    	replytext = replytext.replace("<", "&lt;");
    	replytext = replytext.replace(">", "&gt;");
    	
    	vo.setReplyer(replyer);
    	vo.setReplytext(replytext);
        replyDao.create(vo);
    }
    
    //��� ����
    @Override
    public void update(ReplyVO vo) throws Exception {
    	String replytext = vo.getReplytext();
    	replytext = replytext.replace("<", "&lt;");
    	replytext = replytext.replace(">", "&gt;");
    	replyDao.update(vo);
    }
    
    //��� ����
    @Override
    public void delete(int rno) throws Exception {
    	replyDao.delete(rno);
    }
 
    //���� �Խù��� ��ü ��� �� ��ȸ
    @Override
  	public int count(int id) throws Exception {
    	return replyDao.count(id);
    }
    
    @Override
    public List<ReplyVO> listReplyPage(int id, ReplyPageVO vo) throws Exception {

      return replyDao.listPage(id, vo);
    }
}
