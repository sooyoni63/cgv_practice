package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycgv.dao.CgvMemberDAO;
import com.mycgv.vo.CgvMemberVO;

public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private CgvMemberDAO memberDAO;
	
	
	 /*
	  * �α��� ó��
	  */
	@Override
	public int getLoginResult(CgvMemberVO vo) {
		//CgvMemberDAO dao = new CgvMemberDAO();
		//int result = memberDAO.select(vo);
		return memberDAO.select(vo);
		
	}
	
	/*
	 * ȸ������ ó��
	 */
	@Override
	public int getJoinResult(CgvMemberVO vo) {
		//CgvMemberDAO dao = new CgvMemberDAO();
		//int result = memberDAO.insert(vo);
		return memberDAO.insert(vo);
	}
	
	/*
	 * ȸ�� ��ü ����Ʈ
	 */
	@Override
	public ArrayList<CgvMemberVO> getList(int startCount, int endCount){
		//CgvMemberDAO dao = new CgvMemberDAO();
		ArrayList<CgvMemberVO> list = memberDAO.selectAll(startCount, endCount);
		
		return list;
	}

	
	/*
	 * ȸ�� ��ü��
	 */
	@Override
	public int getTotalCount() {
		//CgvMemberDAO dao = new CgvMemberDAO();
		//int result = memberDAO.totalCount(); 
		return memberDAO.totalCount();
	}
	
	/**
	 * ȸ�� �� ����
	 */
	@Override
	public CgvMemberVO getMemberContent(String id) {
		//CgvMemberDAO dao = new CgvMemberDAO();
		//CgvMemberVO vo = memberDAO.selectContent(id);
		return memberDAO.selectContent(id);
	}
	
	/*
	 * id �ߺ�üũ
	 */

	public int getIdCheck(String id) {
		return memberDAO.idCheck(id);
	}
	
}
	