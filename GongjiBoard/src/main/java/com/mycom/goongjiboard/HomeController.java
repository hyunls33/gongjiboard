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
	private int nowPage;             //�Խ��� ���������� ����
	
	@Inject
    private GongjiService service;   //�Խù� ó�� ����
	
	@Inject
	private ReplyService re_service; //��� ó�� ����(�Խù� �����ÿ��� �̿�)
	
	private static GongjiProvider provider;  //hibernate����ϱ� ����provider����
	
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
        System.out.println(start);
        System.out.println(count);
        if (start < 0) {
        	start = 0;
        }
        List<GongjiVO> list = service.selectGongji(start);//select�� ���� ��ȣ �����ֱ�
        
        model.addAttribute("list", list);                 //�Խù� ���� ����
        model.addAttribute("page", pageVO);               //������ �׺���̼� �����ڷ� ����
        
        logger.info("list");                              //�α����
        return "board/list";                              //���ȭ�� �����ֱ�
	}
	
	//�󼼺���
	@RequestMapping(value="board/view/{id}", method=RequestMethod.GET)
	public ModelAndView viewshow(@PathVariable("id") int id, HttpSession session) throws Exception {
		service.updateViewcnt(id);                        //�Խù� ��ȸ�� �����ͺ��̽��� ������Ʈ ó��
		
        ModelAndView mav = new ModelAndView();            //��(������)+��(ȭ��)�� �Բ� �����ϴ� ��ü
        mav.setViewName("board/view");                    //���� �̸�
        
        //�信 ������ ������
        mav.addObject("dto", service.viewGongji(id));     //�ش� �Խù� ��ȣ�� ��ȸ�ؼ� ������ ����
        mav.addObject("curPage", nowPage);                //�ش� �Խù��� ���� ��������ȣ ����
        return mav;
	}
	
	//�űԱ۾���ȭ��
	@RequestMapping("board/write")
	public String writeshow() {
		return "board/write";                             //�� �ۼ�ȭ�� �����ֱ�
	}
	
	//����ȭ��
	@RequestMapping(value="board/update/{id}", method=RequestMethod.GET)
	public ModelAndView updateshow(@PathVariable("id") int id) throws Exception {
        ModelAndView mav = new ModelAndView();            //��(������)+��(ȭ��)�� �Բ� �����ϴ� ��ü
        mav.setViewName("board/update");                  //���� �̸�
        
        //�信 ������ ������
        mav.addObject("data", service.viewGongji(id));    //�ش� �Խù� ��ȣ�� ��ȸ�ؼ� ������ ����
        mav.addObject("curPage", nowPage);                //�ش� �Խù��� ���� ��������ȣ ����
        return mav;
	}
	
	//����ó��
	@RequestMapping(value="board/update.data", method=RequestMethod.POST)
	public String update(@ModelAttribute GongjiVO vo) throws Exception {		
		service.updateGongji(vo);                         //�Խù� ����ó��
        return "redirect:view/"+vo.getId();               //���� �Ŀ��� �ٽ� �󼼺��� ȭ������ ���ư���
	}
	
	//�űԱ� �ۼ�ó��
	@RequestMapping(value="board/insert", method=RequestMethod.POST)
	public String insertshow(@ModelAttribute GongjiVO vo) throws Exception {
		//service.insertGongji(vo);                       //�Խù� ��� ó��(mybatis��)
		provider = new GongjiProviderImpl();
		provider.insertUser(vo);                          //�Խù� ��� ó��(hibernate��)
        return "redirect:list";                           //�ۼ�ó�� �Ŀ��� ���ȭ������ ���ư���
	}
	
	//�����ϱ�
	@RequestMapping("board/delete")
	public String deleteshow(@RequestParam int id) throws Exception {
		service.deleteGongji(id);                         //�ش� �Խù� ����
		re_service.deleteReply(id);                       //�ش� �Խù��� ���� ��۵� ����
        return "redirect:list?curPage="+nowPage;          //���� ó�� �Ŀ��� ���ȭ������ ���ư���
	}
	
}
