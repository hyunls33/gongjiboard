package com.mycom.service;

import java.util.List;

import com.mycom.dto.GongjiVO;

public interface GongjiService {
	//�Խñ� ��ü���
	public List<GongjiVO> selectGongji(int start) throws Exception;
	//�Խñ� �ű� �ۼ�
    public void insertGongji(GongjiVO vo) throws Exception;
    //�Խñ� �󼼺���
    public GongjiVO viewGongji(int id) throws Exception;
    //�Խñ� �����ϱ�
    public void updateGongji(GongjiVO vo) throws Exception;
	//�Խñ� �����ϱ�
    public void deleteGongji(int id) throws Exception;
	//��ü ��������
	public int boardCount() throws Exception;
	//�Խù� ��ȸ�� ������Ʈ �ϱ�
	public void updateViewcnt(int id) throws Exception;
}