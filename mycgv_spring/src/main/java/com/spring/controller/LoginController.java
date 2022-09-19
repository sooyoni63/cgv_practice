package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.vo.CgvMemberVO;
import com.spring.service.MemberServiceImpl;

@Controller
public class LoginController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	/*
	 * logout.do : �α׾ƿ�
	 */
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		//Session ������ �����ͼ� sid ���� null�� �ƴϸ� session.invalidate �޼ҵ� ȣ��
		String sid = (String)session.getAttribute("sid");
		if(sid != null) {
			session.invalidate(); //���� ���� ����
			mv.addObject("logout_result","ok"); //index ���������� logout_result ���� �޾Ƽ� ok�� ��� alert â�� ���
		}
		mv.setViewName("index");
		return mv;
	}
	
	
	/**
	 * loginCheck.do : �α��� ó��
	 */
	@RequestMapping(value="/loginCheck.do", method=RequestMethod.POST)
	public ModelAndView loginCheck(CgvMemberVO vo, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		 CgvMemberDAO dao = new CgvMemberDAO(); 
		 int result = memberService.getLoginResult(vo); // dao.select(vo);
		 
		
		if(result == 1){
			//�α��� ���� --> session��ü�� key(sid),value(login����) �߰� �� index �������� �̵�
			session.setAttribute("sid", vo.getId());
			
			mv.addObject("login_result","ok");
			mv.setViewName("index");
		}else{
			mv.addObject("login_result","fail");
			mv.setViewName("/login/login");
		}
				
		return mv;
	}
	
	/**
	 * login.do : �α��� ��
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "/login/login";
	}
}
