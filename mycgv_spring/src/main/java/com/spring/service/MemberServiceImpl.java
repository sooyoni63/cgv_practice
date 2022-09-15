package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.vo.CgvMemberVO;

public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private CgvMemberDAO memberDAO;
	
	
	 /*
	  * 로그인 처리
	  */
	@Override
	public int getLoginResult(CgvMemberVO vo) {
		//CgvMemberDAO dao = new CgvMemberDAO();
		//int result = memberDAO.select(vo);
		return memberDAO.select(vo);
		
	}
	
	/*
	 * 회원가입 처리
	 */
	@Override
	public int getJoinResult(CgvMemberVO vo) {
		//CgvMemberDAO dao = new CgvMemberDAO();
		//int result = memberDAO.insert(vo);
		return memberDAO.insert(vo);
	}
	
	/*
	 * 회원 전체 리스트
	 */
	@Override
	public ArrayList<CgvMemberVO> getList(int startCount, int endCount){
		//CgvMemberDAO dao = new CgvMemberDAO();
		ArrayList<CgvMemberVO> list = memberDAO.selectAll(startCount, endCount);
		
		return list;
	}

	
	/*
	 * 회원 전체수
	 */
	@Override
	public int getTotalCount() {
		//CgvMemberDAO dao = new CgvMemberDAO();
		//int result = memberDAO.totalCount(); 
		return memberDAO.totalCount();
	}
	
	/**
	 * 회원 상세 정보
	 */
	@Override
	public CgvMemberVO getMemberContent(String id) {
		//CgvMemberDAO dao = new CgvMemberDAO();
		//CgvMemberVO vo = memberDAO.selectContent(id);
		return memberDAO.selectContent(id);
	}
	
	/*
	 * id 중복체크
	 */

	public int getIdCheck(String id) {
		return memberDAO.idCheck(id);
	}
	
}
	