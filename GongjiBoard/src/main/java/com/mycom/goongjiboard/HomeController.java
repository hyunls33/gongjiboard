package com.mycom.goongjiboard;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.mycom.dto.GongjiVO;
import com.mycom.dto.PageVO;
import com.mycom.hibernate.GongjiProvider;
import com.mycom.hibernate.GongjiProviderImpl;
import com.mycom.service.GongjiService;
import com.mycom.service.ReplyService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private int nowPage;             //게시판 현재페이지 저장
	
	@Inject
    private GongjiService service;   //게시물 처리 서비스
	
	@Inject
	private ReplyService re_service; //댓글 처리 서비스(게시물 삭제시에만 이용)
	
	private static GongjiProvider provider;  //hibernate사용하기 위한provider선언
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//목록보기
	@RequestMapping("board/list")
	public String listshow(Model model, @RequestParam(defaultValue = "1") int curPage) throws Exception {
		
		int count = service.boardCount();                 //게시물 전체갯수 받기
		
		//만약 입력받은 페이지 수가 음수면 1로 반환
		if (curPage <= 0) {
			curPage = 1;
		} 
		
        PageVO pageVO = new PageVO(count, curPage);       //전체 데이터갯수와 현재페이지 수 저장
        nowPage = curPage;
 
        int start = pageVO.getPageBegin();                //select 시작할 번호 페이지 클래스에서 받아오기
        System.out.println(start);
        System.out.println(count);
        if (start < 0) {
        	start = 0;
        }
        List<GongjiVO> list = service.selectGongji(start);//select할 시작 번호 보내주기
        
        model.addAttribute("list", list);                 //게시물 정보 저장
        model.addAttribute("page", pageVO);               //페이지 네비게이션 관련자료 저장
        
        logger.info("list");                              //로그찍기
        return "board/list";                              //목록화면 돌려주기
	}
	
	//상세보기
	@RequestMapping(value="board/view/{id}", method=RequestMethod.GET)
	public ModelAndView viewshow(@PathVariable("id") int id, HttpSession session) throws Exception {
		service.updateViewcnt(id);                        //게시물 조회수 데이터베이스에 업데이트 처리
		
        ModelAndView mav = new ModelAndView();            //모델(데이터)+뷰(화면)를 함께 전달하는 객체
        mav.setViewName("board/view");                    //뷰의 이름
        
        //뷰에 전달할 데이터
        mav.addObject("dto", service.viewGongji(id));     //해당 게시물 번호를 조회해서 데이터 전달
        mav.addObject("curPage", nowPage);                //해당 게시물이 속한 페이지번호 전달
        return mav;
	}
	
	//신규글쓰기화면
	@RequestMapping("board/write")
	public String writeshow() {
		return "board/write";                             //글 작성화면 돌려주기
	}
	
	//수정화면
	@RequestMapping(value="board/update/{id}", method=RequestMethod.GET)
	public ModelAndView updateshow(@PathVariable("id") int id) throws Exception {
        ModelAndView mav = new ModelAndView();            //모델(데이터)+뷰(화면)를 함께 전달하는 객체
        mav.setViewName("board/update");                  //뷰의 이름
        
        //뷰에 전달할 데이터
        mav.addObject("data", service.viewGongji(id));    //해당 게시물 번호를 조회해서 데이터 전달
        mav.addObject("curPage", nowPage);                //해당 게시물이 속한 페이지번호 전달
        return mav;
	}
	
	//수정처리
	@RequestMapping(value="board/update.data", method=RequestMethod.POST)
	public String update(@ModelAttribute GongjiVO vo) throws Exception {		
		service.updateGongji(vo);                         //게시물 수정처리
        return "redirect:view/"+vo.getId();               //수정 후에는 다시 상세보기 화면으로 돌아가기
	}
	
	//신규글 작성처리
	@RequestMapping(value="board/insert", method=RequestMethod.POST)
	public String insertshow(@ModelAttribute GongjiVO vo) throws Exception {
		//service.insertGongji(vo);                       //게시물 등록 처리(mybatis용)
		provider = new GongjiProviderImpl();
		provider.insertUser(vo);                          //게시물 등록 처리(hibernate용)
        return "redirect:list";                           //작성처리 후에는 목록화면으로 돌아가기
	}
	
	//삭제하기
	@RequestMapping("board/delete")
	public String deleteshow(@RequestParam int id) throws Exception {
		service.deleteGongji(id);                         //해당 게시물 삭제
		re_service.deleteReply(id);                       //해당 게시물에 속한 댓글도 삭제
        return "redirect:list?curPage="+nowPage;          //삭제 처리 후에는 목록화면으로 돌아가기
	}
	
}
