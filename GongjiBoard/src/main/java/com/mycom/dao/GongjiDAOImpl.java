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
    
    private static final String Namespace = "com.mycom.mapper.gongjiMapper";
    
    //�Խñ� ��� ��ȸ
    @Override
    public List<GongjiVO> selectGongji(int start) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start);

        return sqlSession.selectList(Namespace+".selectGongji", map);
    }
    
    //�Խñ� �ۼ��ϱ�
    @Override
    public void insertGongji(GongjiVO vo) throws Exception {
        sqlSession.insert(Namespace+".insertGongji", vo);
    }
    
    //�Խñ� �󼼺���
    @Override
    public GongjiVO viewGongji(int id) throws Exception {
        return sqlSession.selectOne(Namespace+".viewGongji", id);
    }
    
    //�Խñ� �����ϱ�
    @Override
    public void updateGongji(GongjiVO vo) throws Exception {
    	sqlSession.update(Namespace+".updateGongji", vo);
    }
    
    //�Խñ� �����ϱ�
    @Override
    public void deleteGongji(int id) throws Exception {
    	sqlSession.delete(Namespace+".deleteGongji", id);
    }

    //��ü �Խù� ��
    @Override
    public int boardCount() {
        return sqlSession.selectOne(Namespace+".boardCount");
    }

    //�Խù� ��ȸ�� ������Ʈ
	@Override
	public void updateViewcnt(int id) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".updateViewcnt", id);
	}
}
