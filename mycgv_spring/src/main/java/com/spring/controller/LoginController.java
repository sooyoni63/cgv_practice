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
	 * logout.do : 로그아웃
	 */
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		//Session 정보를 가져와서 sid 값이 null이 아니면 session.invalidate 메소드 호출
		String sid = (String)session.getAttribute("sid");
		if(sid != null) {
			session.invalidate(); //세션 정보 삭제
			mv.addObject("logout_result","ok"); //index 페이지에서 logout_result 값을 받아서 ok인 경우 alert 창을 띄움
		}
		mv.setViewName("index");
		return mv;
	}
	
	
	/**
	 * loginCheck.do : 로그인 처리
	 */
	@RequestMapping(value="/loginCheck.do", method=RequestMethod.POST)
	public ModelAndView loginCheck(CgvMemberVO vo, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		 CgvMemberDAO dao = new CgvMemberDAO(); 
		 int result = memberService.getLoginResult(vo); // dao.select(vo);
		 
		
		if(result == 1){
			//로그인 성공 --> session객체에 key(sid),value(login계정) 추가 후 index 페이지로 이동
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
	 * login.do : 로그인 폼
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "/login/login";
	}
}
