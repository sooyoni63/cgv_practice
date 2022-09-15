package com.spring.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.vo.CgvBoardVO;
import com.spring.service.BoardServiceImpl;
import com.spring.service.FileServiceImpl;
import com.spring.service.PageServiceImpl;

@Controller
public class BoardController {
	
	@Autowired
	private BoardServiceImpl boardService;
	
	@Autowired
	private PageServiceImpl pageService;
	
	@Autowired
	private FileServiceImpl fileService;	
	
	/**
	 * board_delete_check.do : 게시판 삭제 처리
	 */
	@RequestMapping(value="/board_delete_check.do", method=RequestMethod.POST)
	public ModelAndView board_delete_check(String bid, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		//CgvBoardDAO dao = new CgvBoardDAO();
		//삭제할 bid 행에 bsfile의 이름을 가져오기 위해 dao.select(bid) 메소드 호출--> upload폴더에 파일 유무 확인
		CgvBoardVO vo = boardService.getContent(bid); //dao.select(bid) 메소드는 delete 메소드 호출 전에 실행되어야함!! 
		int result = boardService.getDeleteResult(bid);
		
		if(result == 1){
			//if(!vo.getBsfile().equals("")) {
			//게시글 삭제 시 upload 폴더에 존재하는 파일이 있다면 삭제하기
			/*
			 * if(vo.getBsfile() != null) { 
			 * String path=request.getSession().getServletContext().getRealPath("/"); 
			 * path +="\\resources\\upload\\";
			 * 
			 * File old_file = new File(path+vo.getBsfile()); 
			 * if(old_file.exists()) {
			 * old_file.delete(); } 
			 * }
			 */
			
			fileService.fileDelete(vo, request);
			
			mv.setViewName("redirect:/board_list.do");
		}else{
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	/**
	 * board_update_check.do : 게시판 수정 처리
	 */
	@RequestMapping(value="/board_update_check.do", method=RequestMethod.POST)
	public ModelAndView board_update_check(CgvBoardVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		//기존 파일이 존재하는 경우 이름을 변수로 저장
		String old_filename = vo.getBsfile();
		
		//수정시 새로운 파일을 선택했는지 확인
		/*
		 * if(!vo.getFile1().getOriginalFilename().equals("")) { //새로운 파일선택 O
		 * 
		 * UUID uuid = UUID.randomUUID();
		 * 
		 * vo.setBfile(vo.getFile1().getOriginalFilename());
		 * vo.setBsfile(uuid+"_"+vo.getFile1().getOriginalFilename()); }
		 */				
		
		vo = fileService.update_fileCheck(vo);
		
		
		//CgvBoardDAO dao = new CgvBoardDAO();
		int result = boardService.getUpdateResult(vo);
		if(result == 1){
			//새로운 파일을 upload 폴더에 저장
			/*
			 * if(!vo.getFile1().getOriginalFilename().equals("")) { //새로운 파일선택 O 
			 * Stringpath = request.getSession().getServletContext().getRealPath("/"); 
			 * path += "\\resources\\upload\\";
			 * 
			 * File file = new File(path+vo.getBsfile()); 
			 * vo.getFile1().transferTo(file);
			 * 
			 * //기존파일이 있는 경우에는 파일 삭제 
			 * File ofile = new File(path+old_filename);
			 * if(ofile.exists()) { 
			 * ofile.delete(); 
			 * } 
			 * }
			 */
			
			fileService.update_fileSave(vo, request, old_filename);
			
			
			mv.setViewName("redirect:/board_list.do");
		}else{
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	
	
	/**
	 * board_write_check.do : 게시판 글쓰기 처리
	 */
	@RequestMapping(value="/board_write_check.do", method=RequestMethod.POST)
	public ModelAndView board_write_check(CgvBoardVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		//1. 파일체크 후 bfile, bsfile에 저장되는 이름 생성
		/*
		 * if(vo.getFile1().getOriginalFilename().equals("")) { 
		 * vo.setBfile("");
		 * vo.setBsfile(""); 
		 * }else { 
		 * UUID uuid = UUID.randomUUID();
		 * vo.setBfile(vo.getFile1().getOriginalFilename()); 
		 * vo.setBsfile(uuid + "_" +
		 * vo.getFile1().getOriginalFilename()); }
		 */
		
		vo=fileService.fileCheck(vo);
		
		//CgvBoardDAO dao = new CgvBoardDAO();
		int result = boardService.getWriteResult(vo);//dao.insert(vo);
		
		if(result == 1){
			//2. upload 폴더에 bsfile 명으로 실제 파일 업로드 처리 
			/*
			 * if(!vo.getFile1().getOriginalFilename().equals("")) { 
			 * String path =request.getSession().getServletContext().getRealPath("/"); 
			 * path +="\\resources\\upload\\";
			 * 
			 * File file = new File(path+vo.getBsfile()); 
			 * vo.getFile1().transferTo(file); 
			 * }
			 */
			
			fileService.fileSave(vo, request);
			
			//mv.setViewName("/board/board_list"); //에러X, 아무런 게시글 출력되지 X
			mv.setViewName("redirect:/board_list.do"); //DB연동을 Controller에서 진행하므로, 새로운 연결을 수행!!
		}else{
			mv.setViewName("error_page");
		}
		
		return mv;
	}
	
	/**
	 * board_write.do : 게시판 글쓰기 화면
	 */
	@RequestMapping(value="/board_write.do", method=RequestMethod.GET)
	public String board_write() {
		return "/board/board_write";
	}
	
	/**
	 * board_delete.do : 게시판 삭제 화면
	 */
	@RequestMapping(value="/board_delete.do", method=RequestMethod.GET)
	public ModelAndView board_delete(String bid) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("bid", bid);
		mv.setViewName("/board/board_delete");
		
		return mv;
	}
	
	/**
	 * board_update.do : 게시판 수정 화면
	 */
	@RequestMapping(value="/board_update.do", method=RequestMethod.GET)
	public ModelAndView board_update(String bid) {
		ModelAndView mv = new ModelAndView();
		//String bid = request.getParameter("bid");
		//CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = boardService.getContent(bid);
		
		mv.addObject("vo", vo);
		mv.setViewName("/board/board_update");
		
		return mv;
	}
	
	/**
	 * board_content.do : 게시판 상세 정보
	 */
	@RequestMapping(value="/board_content.do", method=RequestMethod.GET)
	public ModelAndView board_content(String bid) {
		ModelAndView mv = new ModelAndView();
		
		//String bid = request.getParameter("bid");
		//CgvBoardDAO dao = new CgvBoardDAO();
		CgvBoardVO vo = boardService.getContent(bid);//dao.select(bid);
		if(vo != null){
			boardService.getUpdateHits(bid);
		}
		
		mv.addObject("vo", vo);
		mv.setViewName("/board/board_content");
		
		return mv;
	}
	
	/**
	 * board_list.do : 게시판 전체 리스트 
	 */
	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
	public ModelAndView board_list(String rpage) {
		ModelAndView mv = new ModelAndView();
		
		Map<String, Integer> param = pageService.getPageResult(rpage, "board", boardService);
		ArrayList<CgvBoardVO> list = boardService.getList(param.get("startCount"), param.get("endCount"));
		
		mv.addObject("list", list);
		mv.addObject("dbCount", param.get("dbCount"));
		mv.addObject("pageSize", param.get("pageSize"));
		mv.addObject("rpage", param.get("rpage"));
		mv.setViewName("/board/board_list");
		
		return mv;
	}
}











