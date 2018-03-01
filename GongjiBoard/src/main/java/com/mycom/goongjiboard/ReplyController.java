package com.mycom.goongjiboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mycom.dto.RPageVO;
import com.mycom.dto.ReplyVO;
import com.mycom.service.ReplyService;

//REST : Representational State Transfer
//�ϳ��� URI�� �ϳ��� ������ ���ҽ��� ��ǥ�ϵ��� ����� ����

//http://localhost/spring02/list?bno=1 ==> url+�Ķ����
//http://localhost/spring02/list/1 ==> url
//RestController�� ������ 4.0���� ����
//@Controller, @RestController �������� �޼��尡 ����Ǹ� ȭ����ȯ�� ����
@RestController
public class ReplyController {
	 
	@Inject
	ReplyService replyService;

	//��� �Է�
	@RequestMapping("board/reply/insert")
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session) throws Exception {//�α��� ��ɱ����� session�ʿ�
	    replyService.create(vo);
	}

	//��� ���(@RestController Json������� ó�� : �����͸� ����)
	@RequestMapping("board/reply/listJson")
	@ResponseBody // ���ϵ����͸� json���� ��ȯ(��������)
	public Map<String, Object> listJson(@RequestParam int id, @RequestParam(defaultValue = "1") int curPage) throws Exception {
	    Map<String, Object> map = new HashMap<String, Object>();
	    
	    int replyCount = replyService.count(id);          //�ش� id�� ���� �Խù��� �޸� ��� ��ü ���� �ޱ�
	    RPageVO page = new RPageVO(replyCount, curPage);  //��� �� ������ ������������ ����¡ ����ϱ�
	    
	    int start = page.getPageBegin();                  //������ ���۹�ȣ �ޱ�
	    if ((Integer.toString(start) == null) || (start < 0)) {
	    	//������ ���۰��� ���ų� ������ 0���� �����ϱ�
	    	start = 0;
    	}
	    List<ReplyVO> list = replyService.list(id,start); //�ش� �Խù� ��ȣ�� ���� ��� �޾ƿ���
	
	    map.put("list", list);                            //��� ��� ��ȸ ���
	    map.put("page", page);                            //������ ó�� ��� ���
	
	    return map;                                       //������ ����� hashmap���� �������ֱ�
	}
	
	//��� ����
	@RequestMapping(value="board/reply/update", method=RequestMethod.POST)
	public void update(@RequestParam int rno, @RequestParam String replytext) throws Exception {
		ReplyVO vo = new ReplyVO();
        vo.setRno(rno);              //������ ��� ��ȣ �ο�
        vo.setReplytext(replytext);  //��� ���� ���� �ο�
	    replyService.update(vo);     //���� ó��
	}
	
	//��� ����
	@RequestMapping(value="board/reply/delete", method=RequestMethod.POST)
	public void delete(@RequestParam int rno) throws Exception {
	    replyService.delete(rno);    //�ش� ��� ����ó��
	}
}
