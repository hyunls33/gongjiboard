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
    
	//�Խù� ��� ��ȸ
	@Override
	public List<GongjiVO> selectGongji(int start) throws Exception{
		return dao.selectGongji(start);
	}
    
	//�Խñ� �ű� �ۼ�
	@Override
	public void insertGongji(GongjiVO vo) throws Exception {
		String title = vo.getTitle();
        String content = vo.getContent();
        // *�±׹��� ó�� (< ==> &lt; > ==> &gt;)
        // replace(A, B) A�� B�� ����
        title = title.replace("<", "&lt;");
        title = title.replace(">", "&gt;");
        // *���鹮�� ó��
        title = title.replace("  ",    "&nbsp;&nbsp;");
        // *�ٹٲ� ����ó��
        //content = content.replace("\n", "<br>");
        content = content.replace("<", "&lt;");
        content = content.replace(">", "&gt;");
        vo.setTitle(title);
        vo.setContent(content);

    	dao.insertGongji(vo);
    }
	
	//�Խñ� �󼼺���
	@Override
    public GongjiVO viewGongji(int id) throws Exception {
        return dao.viewGongji(id);
    }
    
    //�Խñ� �����ϱ�
    @Override
    public void updateGongji(GongjiVO vo) throws Exception {
    	String title = vo.getTitle();
        String content = vo.getContent();
        // *�±׹��� ó�� (< ==> &lt; > ==> &gt;)
        // replace(A, B) A�� B�� ����
        title = title.replace("<", "&lt;");
        title = title.replace(">", "&gt;");
        // *���鹮�� ó��
        title = title.replace("  ",    "&nbsp;&nbsp;");
        // *�ٹٲ� ����ó��
        //content = content.replace("\n", "<br>");
        content = content.replace("<", "&lt;");
        content = content.replace(">", "&gt;");
        vo.setTitle(title);
        vo.setContent(content);
        
        dao.updateGongji(vo);
    }
    
    //�Խñ� �����ϱ�
    @Override
    public void deleteGongji(int id) throws Exception {
        dao.deleteGongji(id);
    }
    
    //��ü ������ ��
    @Override
    public int boardCount() throws Exception {
    	return dao.boardCount();
    }

    //�Խñ� ��ȸ�� ������Ʈ
	@Override
	public void updateViewcnt(int id) throws Exception {
		dao.updateViewcnt(id);
	}
}
