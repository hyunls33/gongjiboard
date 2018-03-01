package com.mycom.service;

import java.util.List;
import com.mycom.dto.ReplyVO;

public interface ReplyService {
	// 댓글 목록
    public List<ReplyVO> list(int id, int start) throws Exception;
    // 댓글 입력
    public void create(ReplyVO vo) throws Exception;
    // 댓글 수정
    public void update(ReplyVO vo) throws Exception;
    // 댓글 삭제
    public void delete(int rno) throws Exception;
    //게시물 삭제시 해당 게시물의 댓글도 삭제
    public void deleteReply(int rno) throws Exception;
    //현재 게시물의 전체 댓글 수 조회
  	public int count(int id) throws Exception;
}
