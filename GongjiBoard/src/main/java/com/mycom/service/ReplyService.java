package com.mycom.service;

import java.util.List;

import com.mycom.dto.Criteria;
import com.mycom.dto.ReplyPageVO;
import com.mycom.dto.ReplyVO;

public interface ReplyService {
	// 댓글 목록
    //public List<ReplyVO> list(int id, int start) throws Exception;
    public List<ReplyVO> list(int id) throws Exception;
    // 댓글 입력
    public void create(ReplyVO vo) throws Exception;
    // 댓글 수정
    public void update(ReplyVO vo) throws Exception;
    // 댓글 삭제
    public void delete(int rno) throws Exception;
    //현재 게시물의 전체 댓글 수 조회
  	public int count(int id) throws Exception;
  	
  	public List<ReplyVO> listReplyPage(int id, ReplyPageVO vo) throws Exception;
}
