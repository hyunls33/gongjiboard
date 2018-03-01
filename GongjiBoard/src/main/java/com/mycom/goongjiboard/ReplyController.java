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
//하나의 URI가 하나의 고유한 리소스를 대표하도록 설계된 개념

//http://localhost/spring02/list?bno=1 ==> url+파라미터
//http://localhost/spring02/list/1 ==> url
//RestController은 스프링 4.0부터 지원
//@Controller, @RestController 차이점은 메서드가 종료되면 화면전환의 유무
@RestController
public class ReplyController {
	 
	@Inject
	ReplyService replyService;

	//댓글 입력
	@RequestMapping("board/reply/insert")
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session) throws Exception {//로그인 기능구현시 session필요
	    replyService.create(vo);
	}

	//댓글 목록(@RestController Json방식으로 처리 : 데이터를 리턴)
	@RequestMapping("board/reply/listJson")
	@ResponseBody // 리턴데이터를 json으로 변환(생략가능)
	public Map<String, Object> listJson(@RequestParam int id, @RequestParam(defaultValue = "1") int curPage) throws Exception {
	    Map<String, Object> map = new HashMap<String, Object>();
	    
	    int replyCount = replyService.count(id);          //해당 id를 가진 게시물에 달린 댓글 전체 개수 받기
	    RPageVO page = new RPageVO(replyCount, curPage);  //댓글 총 갯수와 헌재페이지로 페이징 계산하기
	    
	    int start = page.getPageBegin();                  //페이지 시작번호 받기
	    if ((Integer.toString(start) == null) || (start < 0)) {
	    	//페이지 시작값이 없거나 음수면 0으로 셋팅하기
	    	start = 0;
    	}
	    List<ReplyVO> list = replyService.list(id,start); //해당 게시물 번호를 가진 댓글 받아오기
	
	    map.put("list", list);                            //댓글 목록 조회 결과
	    map.put("page", page);                            //페이지 처리 계산 결과
	
	    return map;                                       //데이터 결과를 hashmap으로 리턴해주기
	}
	
	//댓글 수정
	@RequestMapping(value="board/reply/update", method=RequestMethod.POST)
	public void update(@RequestParam int rno, @RequestParam String replytext) throws Exception {
		ReplyVO vo = new ReplyVO();
        vo.setRno(rno);              //수정할 댓글 번호 부여
        vo.setReplytext(replytext);  //댓글 내용 새로 부여
	    replyService.update(vo);     //수정 처리
	}
	
	//댓글 삭제
	@RequestMapping(value="board/reply/delete", method=RequestMethod.POST)
	public void delete(@RequestParam int rno) throws Exception {
	    replyService.delete(rno);    //해당 댓글 삭제처리
	}
}
