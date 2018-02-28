package com.mycom.service;

import java.util.List;

import com.mycom.dto.GongjiVO;

public interface GongjiService {
	//게시글 전체목록
	public List<GongjiVO> selectGongji(int start) throws Exception;
	//게시글 신규 작성
    public void insertGongji(GongjiVO vo) throws Exception;
    //게시글 상세보기
    public GongjiVO viewGongji(int id) throws Exception;
    //게시글 수정하기
    public void updateGongji(GongjiVO vo) throws Exception;
	//게시글 삭제하기
    public void deleteGongji(int id) throws Exception;
	//전체 페이지수
	public int boardCount() throws Exception;
	//게시물 조회수 업데이트 하기
	public void updateViewcnt(int id) throws Exception;
}
