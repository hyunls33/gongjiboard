package com.mycom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.mycom.dto.Criteria;

import com.mycom.dto.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
    @Inject
    SqlSession sqlSession;
    private static final String Namespace = "com.mycom.mapper.gongjiMapper";
    
    /*//댓글 목록
    @Override
    public List<ReplyVO> list(int id, int start) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("start", start);

        return sqlSession.selectList(Namespace+".listReply", map);
    }*/
    
    //댓글 목록
    @Override
    public List<ReplyVO> list(int id) throws Exception {
        return sqlSession.selectList(Namespace+".listReply", id);
    }
    
    //댓글 작성
    @Override
    public void create(ReplyVO vo) throws Exception {
        sqlSession.insert(Namespace+".insertReply", vo);
    }
    
    //댓글 수정
    @Override
    public void update(ReplyVO vo) throws Exception {
    	sqlSession.update(Namespace+".updateReply", vo);
    }
    
    //댓글 삭제
    @Override
    public void delete(int rno) throws Exception {
    	sqlSession.selectList(Namespace+".deleteReply", rno);
    }
    
    //조회하는 게시물의 전체 댓글 수 조회
    @Override
    public int count(int id) throws Exception {
        return sqlSession.selectOne(Namespace+".replyCount");
    }
    
    @Override
    public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {

      Map<String, Object> paramMap = new HashMap<String, Object>();

      paramMap.put("bno", bno);
      paramMap.put("cri", cri);

      return sqlSession.selectList(Namespace + ".listPage", paramMap);
    }
}
