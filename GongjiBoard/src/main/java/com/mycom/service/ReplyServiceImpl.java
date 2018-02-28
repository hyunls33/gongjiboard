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
    
    /*//댓글 목록
    @Override
    public List<ReplyVO> list(int id, int start) throws Exception {
        return replyDao.list(id, start);
    }*/
    //댓글 목록
    @Override
    public List<ReplyVO> list(int id) throws Exception {
        return replyDao.list(id);
    }
    
    //댓글 작성
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
    
    //댓글 수정
    @Override
    public void update(ReplyVO vo) throws Exception {
    	String replytext = vo.getReplytext();
    	replytext = replytext.replace("<", "&lt;");
    	replytext = replytext.replace(">", "&gt;");
    	replyDao.update(vo);
    }
    
    //댓글 삭제
    @Override
    public void delete(int rno) throws Exception {
    	replyDao.delete(rno);
    }
 
    //현재 게시물의 전체 댓글 수 조회
    @Override
  	public int count(int id) throws Exception {
    	return replyDao.count(id);
    }
    
    @Override
    public List<ReplyVO> listReplyPage(int id, ReplyPageVO vo) throws Exception {

      return replyDao.listPage(id, vo);
    }
}
