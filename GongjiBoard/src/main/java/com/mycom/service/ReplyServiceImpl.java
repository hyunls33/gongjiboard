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
    
    //��� ���
    @Override
    public List<ReplyVO> list(int id, int start) throws Exception {
        return replyDao.list(id, start);
    }

    //��� �ۼ�
    @Override
    public void create(ReplyVO vo) throws Exception {
    	String replyer = vo.getReplyer();
    	String replytext = vo.getReplytext();
    	//��� �ۼ��ڿ� ��� ���� �±� �ٲ�ó��
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
    	//��� ���� �±� �ٲ�ó��
    	replytext = replytext.replace("<", "&lt;");
    	replytext = replytext.replace(">", "&gt;");
    	replyDao.update(vo);
    }
    
    //��� ����
    @Override
    public void delete(int rno) throws Exception {
    	replyDao.delete(rno);
    }
    
    //�Խù� ������ �ش� �Խù��� ��۵� ����
    @Override
    public void deleteReply(int rno) throws Exception {
    	replyDao.deleteReply(rno);
    }
 
    //���� �Խù��� ��ü ��� �� ��ȸ
    @Override
  	public int count(int id) throws Exception {
    	return replyDao.count(id);
    }
}
