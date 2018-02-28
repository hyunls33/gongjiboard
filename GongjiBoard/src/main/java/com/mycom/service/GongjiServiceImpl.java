package com.mycom.service;

import java.util.List;

import javax.inject.Inject;
 
import org.springframework.stereotype.Service;
 
import com.mycom.dao.GongjiDAO;
import com.mycom.dto.GongjiVO;

@Service("GongjiService")
public class GongjiServiceImpl implements GongjiService {
	@Inject
    private GongjiDAO dao;
    
	//게시물 목록 조회
	@Override
	public List<GongjiVO> selectGongji(int start) throws Exception{
		return dao.selectGongji(start);
	}
    
	//게시글 신규 작성
	@Override
	public void insertGongji(GongjiVO vo) throws Exception {
		String title = vo.getTitle();
        String content = vo.getContent();
        // *태그문자 처리 (< ==> &lt; > ==> &gt;)
        // replace(A, B) A를 B로 변경
        title = title.replace("<", "&lt;");
        title = title.replace(">", "&gt;");
        // *공백문자 처리
        title = title.replace("  ",    "&nbsp;&nbsp;");
        // *줄바꿈 문자처리
        //content = content.replace("\n", "<br>");
        content = content.replace("<", "&lt;");
        content = content.replace(">", "&gt;");
        vo.setTitle(title);
        vo.setContent(content);

    	dao.insertGongji(vo);
    }
	
	//게시글 상세보기
	@Override
    public GongjiVO viewGongji(int id) throws Exception {
        return dao.viewGongji(id);
    }
    
    //게시글 수정하기
    @Override
    public void updateGongji(GongjiVO vo) throws Exception {
    	String title = vo.getTitle();
        String content = vo.getContent();
        // *태그문자 처리 (< ==> &lt; > ==> &gt;)
        // replace(A, B) A를 B로 변경
        title = title.replace("<", "&lt;");
        title = title.replace(">", "&gt;");
        // *공백문자 처리
        title = title.replace("  ",    "&nbsp;&nbsp;");
        // *줄바꿈 문자처리
        //content = content.replace("\n", "<br>");
        content = content.replace("<", "&lt;");
        content = content.replace(">", "&gt;");
        vo.setTitle(title);
        vo.setContent(content);
        
        dao.updateGongji(vo);
    }
    
    //게시글 삭제하기
    @Override
    public void deleteGongji(int id) throws Exception {
        dao.deleteGongji(id);
    }
    
    //전체 페이지 수
    @Override
    public int boardCount() throws Exception {
    	return dao.boardCount();
    }

    //게시글 조회수 업데이트
	@Override
	public void updateViewcnt(int id) throws Exception {
		dao.updateViewcnt(id);
	}
}
