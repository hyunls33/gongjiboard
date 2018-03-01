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
    
    //��� ���
    @Override
    public List<ReplyVO> list(int id, int start) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("start", start);

        return sqlSession.selectList(Namespace+".listReply", map);     //replyMapper�� id�� listReply�� �±׿� ����
    }
    
    //��� �ۼ�
    @Override
    public void create(ReplyVO vo) throws Exception {
        sqlSession.insert(Namespace+".insertReply", vo);               //replyMapper�� id�� insertReply�� �±׿� ����
    }
    
    //��� ����
    @Override
    public void update(ReplyVO vo) throws Exception {
    	sqlSession.update(Namespace+".updateReply", vo);               //replyMapper�� id�� updateReply�� �±׿� ����
    }
    
    //��� ����
    @Override
    public void delete(int rno) throws Exception {
    	sqlSession.selectList(Namespace+".deleteReply", rno);          //replyMapper�� id�� deleteReply�� �±׿� ����
    }
    
    //�Խù� ������ �ش� �Խù��� ��۵� ����
    @Override
    public void deleteReply(int rno) throws Exception {
    	sqlSession.delete(Namespace+".deleteGongjiReply", rno);        //replyMapper�� id�� deleteGongjiReply�� �±׿� ����
    }
    
    //��ȸ�ϴ� �Խù��� ��ü ��� �� ��ȸ
    @Override
    public int count(int id) throws Exception {
        return sqlSession.selectOne(Namespace+".replyCount", id);      //replyMapper�� id�� replyCount�� �±׿� ����
    }
}
