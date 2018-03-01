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
    
    private static final String Namespace = "com.mycom.mapper.gongjiMapper";//mapper이름 명시
    
    //게시글 목록 조회
    @Override
    public List<GongjiVO> selectGongji(int start) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start);

        return sqlSession.selectList(Namespace+".selectGongji", map); //gongjiMapper에 id가 selectGongji인 태그와 연결
    }
    
    //게시글 작성하기
    @Override
    public void insertGongji(GongjiVO vo) throws Exception {
        sqlSession.insert(Namespace+".insertGongji", vo);             //gongjiMapper에 id가 insertGongji인 태그와 연결
    }
    
    //게시글 상세보기
    @Override
    public GongjiVO viewGongji(int id) throws Exception {
        return sqlSession.selectOne(Namespace+".viewGongji", id);     //gongjiMapper에 id가 viewGongji인 태그와 연결
    }
    
    //게시글 수정하기
    @Override
    public void updateGongji(GongjiVO vo) throws Exception {
    	sqlSession.update(Namespace+".updateGongji", vo);             //gongjiMapper에 id가 updateGongji인 태그와 연결
    }
    
    //게시글 삭제하기
    @Override
    public void deleteGongji(int id) throws Exception {
    	sqlSession.delete(Namespace+".deleteGongji", id);             //gongjiMapper에 id가 deleteGongji인 태그와 연결
    }

    //전체 게시물 수
    @Override
    public int boardCount() {
        return sqlSession.selectOne(Namespace+".boardCount");         //gongjiMapper에 id가 boardCount인 태그와 연결
    }

    //게시물 조회수 업데이트
	@Override
	public void updateViewcnt(int id) throws Exception {
		sqlSession.update(Namespace+".updateViewcnt", id);            //gongjiMapper에 id가 updateViewcnt인 태그와 연결
	}
}
