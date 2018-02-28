package com.mycom.goongjiboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mycom.dto.Criteria;
import com.mycom.dto.PageMaker;
import com.mycom.dto.ReplyPageVO;
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
	
	private int nowPage;//��� ���������� ����

	//��� �Է�
	@RequestMapping("board/reply/insert")
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session) throws Exception {//�α��� ��ɱ����� session�ʿ�
	    replyService.create(vo);
	}
	
	//��� ���(@RestController Json������� ó�� : �����͸� ����)
	@RequestMapping("board/reply/listJson")
	@ResponseBody // ���ϵ����͸� json���� ��ȯ(��������)
	public List<ReplyVO> listJson(@RequestParam int id) throws Exception {
	    List<ReplyVO> list = replyService.list(id);
	    return list;
	}

	//��� ����
	@RequestMapping(value="board/reply/update", method=RequestMethod.POST)
	public void update(@RequestParam int rno, @RequestParam String replytext) throws Exception {
		ReplyVO vo = new ReplyVO();
        vo.setRno(rno);
        vo.setReplytext(replytext);
	    replyService.update(vo);
	}
	
	//��� ����
	@RequestMapping(value="board/reply/delete", method=RequestMethod.POST)
	public void delete(@RequestParam int rno) throws Exception {
	    replyService.delete(rno);
	}
	
	@RequestMapping(value = "board/reply/{id}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(
    @PathVariable("id") Integer id,
    @PathVariable("page") Integer page) {

	    ResponseEntity<Map<String, Object>> entity = null;
	    
	    try {
		    Map<String, Object> map = new HashMap<String, Object>();
		    
		    int replyCount = replyService.count(id);
		    ReplyPageVO pageMaker = new ReplyPageVO(replyCount, page);
		    
		    List<ReplyVO> list = replyService.listReplyPage(id, pageMaker);
		
		    map.put("list", list);
		    map.put("pageMaker", pageMaker);
		
		    entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		
	    } catch (Exception e) {
		    e.printStackTrace();
		    entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
	    }
	    return entity;
    }
}
