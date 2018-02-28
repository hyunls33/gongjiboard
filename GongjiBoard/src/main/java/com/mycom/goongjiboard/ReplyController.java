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
//하나의 URI가 하나의 고유한 리소스를 대표하도록 설계된 개념

//http://localhost/spring02/list?bno=1 ==> url+파라미터
//http://localhost/spring02/list/1 ==> url
//RestController은 스프링 4.0부터 지원
//@Controller, @RestController 차이점은 메서드가 종료되면 화면전환의 유무
@RestController
public class ReplyController {
	 
	@Inject
	ReplyService replyService;
	
	private int nowPage;//댓글 현재페이지 저장

	//댓글 입력
	@RequestMapping("board/reply/insert")
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session) throws Exception {//로그인 기능구현시 session필요
	    replyService.create(vo);
	}
	
	//댓글 목록(@RestController Json방식으로 처리 : 데이터를 리턴)
	@RequestMapping("board/reply/listJson")
	@ResponseBody // 리턴데이터를 json으로 변환(생략가능)
	public List<ReplyVO> listJson(@RequestParam int id) throws Exception {
	    List<ReplyVO> list = replyService.list(id);
	    return list;
	}

	//댓글 수정
	@RequestMapping(value="board/reply/update", method=RequestMethod.POST)
	public void update(@RequestParam int rno, @RequestParam String replytext) throws Exception {
		ReplyVO vo = new ReplyVO();
        vo.setRno(rno);
        vo.setReplytext(replytext);
	    replyService.update(vo);
	}
	
	//댓글 삭제
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
