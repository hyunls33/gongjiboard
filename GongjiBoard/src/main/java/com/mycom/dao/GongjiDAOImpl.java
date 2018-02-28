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
    
    //게시글 목록 조회
    @Override
    public List<GongjiVO> selectGongji(int start) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start);

        return sqlSession.selectList(Namespace+".selectGongji", map);
    }
    
    //게시글 작성하기
    @Override
    public void insertGongji(GongjiVO vo) throws Exception {
        sqlSession.insert(Namespace+".insertGongji", vo);
    }
    
    //게시글 상세보기
    @Override
    public GongjiVO viewGongji(int id) throws Exception {
        return sqlSession.selectOne(Namespace+".viewGongji", id);
    }
    
    //게시글 수정하기
    @Override
    public void updateGongji(GongjiVO vo) throws Exception {
    	sqlSession.update(Namespace+".updateGongji", vo);
    }
    
    //게시글 삭제하기
    @Override
    public void deleteGongji(int id) throws Exception {
    	sqlSession.delete(Namespace+".deleteGongji", id);
    }

    //전체 게시물 수
    @Override
    public int boardCount() {
        return sqlSession.selectOne(Namespace+".boardCount");
    }

    //게시물 조회수 업데이트
	@Override
	public void updateViewcnt(int id) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Namespace+".updateViewcnt", id);
	}
}
