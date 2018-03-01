package com.mycom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.mycom.dto.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
    @Inject
    SqlSession sqlSession;
    private static final String Namespace = "com.mycom.mapper.replyMapper";
    
    //댓글 목록
    @Override
    public List<ReplyVO> list(int id, int start) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("start", start);

        return sqlSession.selectList(Namespace+".listReply", map);     //replyMapper에 id가 listReply인 태그와 연결
    }
    
    //댓글 작성
    @Override
    public void create(ReplyVO vo) throws Exception {
        sqlSession.insert(Namespace+".insertReply", vo);               //replyMapper에 id가 insertReply인 태그와 연결
    }
    
    //댓글 수정
    @Override
    public void update(ReplyVO vo) throws Exception {
    	sqlSession.update(Namespace+".updateReply", vo);               //replyMapper에 id가 updateReply인 태그와 연결
    }
    
    //댓글 삭제
    @Override
    public void delete(int rno) throws Exception {
    	sqlSession.selectList(Namespace+".deleteReply", rno);          //replyMapper에 id가 deleteReply인 태그와 연결
    }
    
    //게시물 삭제시 해당 게시물의 댓글도 삭제
    @Override
    public void deleteReply(int rno) throws Exception {
    	sqlSession.delete(Namespace+".deleteGongjiReply", rno);        //replyMapper에 id가 deleteGongjiReply인 태그와 연결
    }
    
    //조회하는 게시물의 전체 댓글 수 조회
    @Override
    public int count(int id) throws Exception {
        return sqlSession.selectOne(Namespace+".replyCount", id);      //replyMapper에 id가 replyCount인 태그와 연결
    }
}
