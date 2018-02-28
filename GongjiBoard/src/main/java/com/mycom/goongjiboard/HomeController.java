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
import com.mycom.service.GongjiService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private int nowPage;//�Խ��� ���������� ����
	
	@Inject
    private GongjiService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//��Ϻ���
	@RequestMapping("board/list")
	public String listshow(Model model, @RequestParam(defaultValue = "1") int curPage) throws Exception {
		
		int count = service.boardCount();                 //�Խù� ��ü���� �ޱ�
		
		//���� �Է¹��� ������ ���� ������ 1�� ��ȯ
		if (curPage <= 0) {
			curPage = 1;
		} 
		
        PageVO pageVO = new PageVO(count, curPage);       //��ü �����Ͱ����� ���������� �� ����
        nowPage = curPage;
 
        int start = pageVO.getPageBegin();                //select ������ ��ȣ ������ Ŭ�������� �޾ƿ���
        List<GongjiVO> list = service.selectGongji(start);//select�� ���� ��ȣ �����ֱ�
        
        model.addAttribute("list", list);                 //�Խù� ���� ����
        model.addAttribute("page", pageVO);               //������ �׺���̼� �����ڷ� ����
        
        logger.info("list");//�α����
        return "board/list";
	}
	
	//�󼼺���
	@RequestMapping(value="board/view/{id}", method=RequestMethod.GET)
	public ModelAndView viewshow(@PathVariable("id") int id, HttpSession session) throws Exception {
		service.updateViewcnt(id);//�Խù� ��ȸ�� �����ͺ��̽��� ������Ʈ ó��
		//��(������)+��(ȭ��)�� �Բ� �����ϴ� ��ü
        ModelAndView mav = new ModelAndView();
        //���� �̸�
        mav.setViewName("board/view");
        //�信 ������ ������
        mav.addObject("dto", service.viewGongji(id));
        mav.addObject("curPage", nowPage);
        return mav;
	}
	
	//�űԱ۾���ȭ��
	@RequestMapping("board/write")
	public String writeshow() {
		
		return "board/write";
	}
	
	//����ȭ��
	@RequestMapping(value="board/update/{id}", method=RequestMethod.GET)
	public ModelAndView updateshow(@PathVariable("id") int id) throws Exception {
		//��(������)+��(ȭ��)�� �Բ� �����ϴ� ��ü
        ModelAndView mav = new ModelAndView();
        //���� �̸�
        mav.setViewName("board/update");
        //�信 ������ ������
        mav.addObject("data", service.viewGongji(id));
        mav.addObject("curPage", nowPage);
        
        return mav;
	}
	
	//����ó��
	@RequestMapping(value="board/update.data", method=RequestMethod.POST)
	public String update(@ModelAttribute GongjiVO vo) throws Exception {		
		service.updateGongji(vo);
        return "redirect:view/"+vo.getId();
	}
	
	//�űԱ� �ۼ�ó��
	@RequestMapping(value="board/insert", method=RequestMethod.POST)
	public String insertshow(@ModelAttribute GongjiVO vo) throws Exception {
		service.insertGongji(vo);
        return "redirect:list";
	}
	
	//�����ϱ�
	@RequestMapping("board/delete")
	public String deleteshow(@RequestParam int id) throws Exception {
		service.deleteGongji(id);
        return "redirect:list?curPage="+nowPage;
	}
	
}
