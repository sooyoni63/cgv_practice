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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.dao.CgvNoticeDAO;
import com.mycgv.vo.CgvMemberVO;
import com.mycgv.vo.CgvMovieVO;
import com.mycgv.vo.CgvNoticeVO;
import com.spring.service.FileServiceImpl;
import com.spring.service.MemberServiceImpl;
import com.spring.service.MovieServiceImpl;
import com.spring.service.NoticeServiceImpl;
import com.spring.service.PageServiceImpl;

@Controller
public class AdminController {

	@Autowired
	private MemberServiceImpl memberService;

	@Autowired
	private NoticeServiceImpl noticeService;

	@Autowired
	private MovieServiceImpl movieService;

	@Autowired
	private PageServiceImpl pageService;

	@Autowired
	private FileServiceImpl fileService;

	/**
	 * admin_movie_content.do : ��ȭ �� ����
	 */
	@RequestMapping(value = "/admin_movie_content.do", method = RequestMethod.GET)
	public ModelAndView admin_movie_content(String mid) {
		ModelAndView mv = new ModelAndView();
		// CgvMovieDAO dao = new CgvMovieDAO();
		CgvMovieVO vo = movieService.getContent(mid);

		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_movie/admin_movie_content");

		return mv;
	}

	/**
	 * admin_movie_regist_check.do : ��ȭ ��� ó��
	 */
	@RequestMapping(value = "/admin_movie_regist_check.do", method = RequestMethod.POST)
	public ModelAndView admin_movie_regist_check(CgvMovieVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();

		// ������ �����ߴ��� üũ
		/*
		 * for (CommonsMultipartFile file : vo.getFiles()) { if
		 * (!file.getOriginalFilename().equals("")) { UUID uuid = UUID.randomUUID();
		 * 
		 * vo.getMfiles().add(file.getOriginalFilename()); vo.getMsfiles().add(uuid +
		 * "_" + file.getOriginalFilename()); } else { vo.getMfiles().add("");
		 * vo.getMsfiles().add(""); } } vo.setMfile1(vo.getMfiles().get(0));
		 * vo.setMfile2(vo.getMfiles().get(1)); vo.setMsfile1(vo.getMsfiles().get(0));
		 * vo.setMsfile2(vo.getMsfiles().get(1));
		 */

		vo = fileService.multiFileCheck(vo);
		// DB ����
		// CgvMovieDAO dao = new CgvMovieDAO();
		// 1. cgv_movie ���̺� ���� --> mid ����
		int result = movieService.getRegistResult(vo);

		if (result == 1) {
			// 2. mid���� ��������
			String mid = movieService.getSelectMid();
			// System.out.println(mid);

			// 3. mid�� ���۷����ϴ� cgv_movie_file ���̺� ����
			vo.setMid(mid);
			int result2 = movieService.getRegistFile(vo);

			if (result2 == 1) {
				// ������ upload�� ����
				/*
				 * for (int i = 0; i < vo.getFiles().length; i++) { CommonsMultipartFile file =
				 * vo.getFiles()[i];
				 * 
				 * if (!file.getOriginalFilename().equals("")) { String path =
				 * request.getSession().getServletContext().getRealPath("/"); path +=
				 * "\\resources\\upload\\";
				 * 
				 * File upload_file = new File(path + vo.getMsfiles().get(i));
				 * file.transferTo(upload_file); } } // for
				 * 
				 */
				fileService.multiFileSave(vo, request);

			}

		}

		mv.setViewName("redirect:/admin_movie_list.do");

		return mv;
	}

	/**
	 * admin_movie_regist.do : ��ȭ ��� ȭ��
	 */
	@RequestMapping(value = "/admin_movie_regist.do", method = RequestMethod.GET)
	public ModelAndView admin_movie_regist() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("/admin/admin_movie/admin_movie_regist");

		return mv;
	}

	/**
	 * admin_movie_list.do : ��ü ��ȭ ����Ʈ
	 */
	@RequestMapping(value = "/admin_movie_list.do", method = RequestMethod.GET)
	public ModelAndView admin_movie_list(String rpage) {
		ModelAndView mv = new ModelAndView();

		Map<String, Integer> param = pageService.getPageResult(rpage, "movie", movieService);

		ArrayList<CgvMovieVO> list = movieService.getList(param.get("startCount"), param.get("endCount"));

		mv.addObject("list", list);
		mv.addObject("dbCount", param.get("dbCount"));
		mv.addObject("rpage", param.get("rpage"));
		mv.addObject("pageSize", param.get("pageSize"));
		mv.setViewName("/admin/admin_movie/admin_movie_list");

		return mv;
	}

	/**
	 * admin_notice_delete_check.do : �������� ���� ó��
	 */
	@RequestMapping(value = "/admin_notice_delete_check.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_delete_check(String nid, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		// DB����
		// CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = noticeService.getContent(nid);
		int result = noticeService.getDeleteResult(nid);

		if (result == 1) {
			/*
			 * if (vo.getNsfile() != null) { String path =
			 * request.getSession().getServletContext().getRealPath("/"); path +=
			 * "\\resources\\upload\\";
			 * 
			 * File old_file = new File(path + vo.getNsfile()); if(old_file.exists()) {
			 * old_file.delete(); } }
			 */
			fileService.fileDelete(vo, request);
			mv.setViewName("redirect:/admin_notice_list.do");
		} else {

			mv.setViewName("error_page");
		}

		return mv;
	}

	/**
	 * admin_notice_update_check.do : �������� ���� ó��
	 */
	@RequestMapping(value = "/admin_notice_update_check.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_update_check(CgvNoticeVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();

		String old_filename = vo.getNsfile(); // ����ȭ�鿡�� hidden���� �Ѿ���� ���� upload ������ ����� ���ϸ�

		/*
		 * if (!vo.getFile1().getOriginalFilename().equals("")) { // ���ο� ������ ������ ��� UUID
		 * uuid = UUID.randomUUID();
		 * 
		 * vo.setNfile(vo.getFile1().getOriginalFilename()); vo.setNsfile(uuid + "_" +
		 * vo.getFile1().getOriginalFilename()); }
		 */

		vo = fileService.update_fileCheck(vo);

		// CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = noticeService.getUpdateResult(vo);
		if (result == 1) {
			// ���ο� ������ upload ������ ������ �� ������ ������ ����
			/*
			 * if (!vo.getFile1().getOriginalFilename().equals("")) { // ���ο� ������ ������ ���
			 * String path = request.getSession().getServletContext().getRealPath("/"); path
			 * += "\\resources\\upload\\";
			 * 
			 * File new_file = new File(path + vo.getNsfile());
			 * vo.getFile1().transferTo(new_file);
			 * 
			 * // ���� ���� ���� File old_file = new File(path + old_filename);
			 * if(old_file.exists()) { old_file.delete(); } }
			 */
			fileService.update_fileSave(vo, request, old_filename);
			mv.setViewName("redirect:/admin_notice_list.do");
		} else {

			mv.setViewName("error_page");
		}

		return mv;
	}

	/**
	 * admin_notice_write_check.do : �������� �۾��� ó��
	 */
	@RequestMapping(value = "/admin_notice_write_check.do", method = RequestMethod.POST)
	public ModelAndView admin_notice_write_check(CgvNoticeVO vo, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();

		/*
		 * if(vo.getFile1().getOriginalFilename().equals("")) { vo.setNfile("");
		 * vo.setNsfile(""); }else { UUID uuid = UUID.randomUUID();
		 * 
		 * vo.setNfile(vo.getFile1().getOriginalFilename());
		 * vo.setNsfile(uuid+"_"+vo.getFile1().getOriginalFilename());
		 * 
		 * }
		 */

		vo = fileService.fileCheck(vo);

		// DB����
		// CgvNoticeDAO dao = new CgvNoticeDAO();
		int result = noticeService.getWriteResult(vo);
		if (result == 1) {
			/*
			 * if(!vo.getFile1().getOriginalFilename().equals("")) { String path =
			 * request.getSession().getServletContext().getRealPath("/"); path +=
			 * "\\resources\\upload\\";
			 * 
			 * File file = new File(path+vo.getNsfile()); vo.getFile1().transferTo(file); }
			 */
			fileService.fileSave(vo, request);
			mv.setViewName("redirect:/admin_notice_list.do");
		} else {

			mv.setViewName("error_page");
		}

		return mv;
	}

	/**
	 * admin_notice_write.do : �������� �۾��� ȭ��
	 */
	@RequestMapping(value = "/admin_notice_write.do", method = RequestMethod.GET)
	public String admin_notice_write() {
		return "/admin/admin_notice/admin_notice_write";
	}

	/**
	 * admin_notice_delete.do : �������� ���� ȭ��
	 */
	@RequestMapping(value = "/admin_notice_delete.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_delete(String nid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("nid", nid);
		mv.setViewName("/admin/admin_notice/admin_notice_delete");

		return mv;
	}

	/**
	 * admin_notice_update.do : �������� ����ȭ��
	 */
	@RequestMapping(value = "/admin_notice_update.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_update(String nid) {
		ModelAndView mv = new ModelAndView();

		CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = noticeService.getContent(nid);

		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_notice/admin_notice_update");

		return mv;
	}

	/**
	 * admin_notice_content.do : �������� �� ����
	 */
	@RequestMapping(value = "/admin_notice_content.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_content(String nid) {
		ModelAndView mv = new ModelAndView();

		// String nid = request.getParameter("nid");
		// CgvNoticeDAO dao = new CgvNoticeDAO();
		CgvNoticeVO vo = noticeService.getContent(nid);
		if(vo != null) {
			noticeService.getUpdateHits(nid);
		}

		mv.addObject("vo", vo);
		mv.setViewName("/admin/admin_notice/admin_notice_content");

		return mv;
	}

	/**
	 * admin_notice_list.do : �������� ��ü����Ʈ
	 */
	@RequestMapping(value = "/admin_notice_list.do", method = RequestMethod.GET)
	public ModelAndView admin_notice_list(String rpage) {
		ModelAndView mv = new ModelAndView();

		Map<String, Integer> param = pageService.getPageResult(rpage, "notice", noticeService);
		ArrayList<CgvNoticeVO> list = noticeService.getList(param.get("startCount"), param.get("endCount"));

		mv.addObject("list", list);
		mv.addObject("dbCount", param.get("dbCount"));
		mv.addObject("rpage", param.get("rpage"));
		mv.addObject("pageSize", param.get("pageSize"));
		mv.setViewName("/admin/admin_notice/admin_notice_list");

		return mv;
	}

	/**
	 * admin_member_content.do : ȸ�� �� ����
	 */
	@RequestMapping(value = "/admin_member_content.do", method = RequestMethod.GET)
	public ModelAndView admin_member_content(String id) {
		ModelAndView mv = new ModelAndView();

		CgvMemberDAO dao = new CgvMemberDAO();
		CgvMemberVO vo = memberService.getMemberContent(id);// dao.selectContent(id);
		String address = vo.getZonecode() + " " + vo.getAddr1() + " " + vo.getAddr2();

		mv.addObject("vo", vo);
		mv.addObject("address", address);
		mv.setViewName("/admin/admin_member/admin_member_content");

		return mv;
	}

	/**
	 * admin_member_list.do : ȸ�� ��ü����Ʈ
	 */
	@RequestMapping(value = "/admin_member_list.do", method = RequestMethod.GET)
	public ModelAndView admin_member_list(String rpage) {
		ModelAndView mv = new ModelAndView();

		Map<String, Integer> param = pageService.getPageResult(rpage, "member", memberService);

		ArrayList<CgvMemberVO> list = memberService.getList(param.get("startCount"), param.get("endCount"));

		mv.addObject("list", list);
		mv.addObject("dbCount", param.get("dbCount"));
		mv.addObject("rpage", param.get("rpage"));
		mv.addObject("pageSize", param.get("pageSize"));
		mv.setViewName("/admin/admin_member/admin_member_list");

		return mv;
	}

	/**
	 * admin.do : ������ ������
	 */
	@RequestMapping(value = "/admin.do", method = RequestMethod.GET)
	public String admin() {
		return "/admin/admin";
	}
}
