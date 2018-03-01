package com.mycom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.mycom.dto.GongjiVO;

@Repository
public class GongjiDAOImpl implements GongjiDAO {

	@Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.mycom.mapper.gongjiMapper";//mapper�̸� ���
    
    //�Խñ� ��� ��ȸ
    @Override
    public List<GongjiVO> selectGongji(int start) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start);

        return sqlSession.selectList(Namespace+".selectGongji", map); //gongjiMapper�� id�� selectGongji�� �±׿� ����
    }
    
    //�Խñ� �ۼ��ϱ�
    @Override
    public void insertGongji(GongjiVO vo) throws Exception {
        sqlSession.insert(Namespace+".insertGongji", vo);             //gongjiMapper�� id�� insertGongji�� �±׿� ����
    }
    
    //�Խñ� �󼼺���
    @Override
    public GongjiVO viewGongji(int id) throws Exception {
        return sqlSession.selectOne(Namespace+".viewGongji", id);     //gongjiMapper�� id�� viewGongji�� �±׿� ����
    }
    
    //�Խñ� �����ϱ�
    @Override
    public void updateGongji(GongjiVO vo) throws Exception {
    	sqlSession.update(Namespace+".updateGongji", vo);             //gongjiMapper�� id�� updateGongji�� �±׿� ����
    }
    
    //�Խñ� �����ϱ�
    @Override
    public void deleteGongji(int id) throws Exception {
    	sqlSession.delete(Namespace+".deleteGongji", id);             //gongjiMapper�� id�� deleteGongji�� �±׿� ����
    }

    //��ü �Խù� ��
    @Override
    public int boardCount() {
        return sqlSession.selectOne(Namespace+".boardCount");         //gongjiMapper�� id�� boardCount�� �±׿� ����
    }

    //�Խù� ��ȸ�� ������Ʈ
	@Override
	public void updateViewcnt(int id) throws Exception {
		sqlSession.update(Namespace+".updateViewcnt", id);            //gongjiMapper�� id�� updateViewcnt�� �±׿� ����
	}
}
