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
        replyDao.create(vo);
    }
    
    //댓글 수정
    @Override
    public void update(ReplyVO vo) throws Exception {
        // TODO Auto-generated method stub
    	replyDao.update(vo);
    }
    
    //댓글 삭제
    @Override
    public void delete(int rno) throws Exception {
        // TODO Auto-generated method stub
    	replyDao.delete(rno);
    }
 
    //현재 게시물의 전체 댓글 수 조회
    @Override
  	public int count(int id) throws Exception {
    	return replyDao.count(id);
    }
}
