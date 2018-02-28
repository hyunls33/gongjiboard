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
    
    /*//��� ���
    @Override
    public List<ReplyVO> list(int id, int start) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("start", start);

        return sqlSession.selectList(Namespace+".listReply", map);
    }*/
    
    //��� ���
    @Override
    public List<ReplyVO> list(int id) throws Exception {
        return sqlSession.selectList(Namespace+".listReply", id);
    }
    
    //��� �ۼ�
    @Override
    public void create(ReplyVO vo) throws Exception {
        sqlSession.insert(Namespace+".insertReply", vo);
    }
    
    //��� ����
    @Override
    public void update(ReplyVO vo) throws Exception {
    	sqlSession.update(Namespace+".updateReply", vo);
    }
    
    //��� ����
    @Override
    public void delete(int rno) throws Exception {
    	sqlSession.selectList(Namespace+".deleteReply", rno);
    }
    
    //��ȸ�ϴ� �Խù��� ��ü ��� �� ��ȸ
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
