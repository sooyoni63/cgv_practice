	package com.spring.service;
	
	import java.util.ArrayList;

import com.mycgv.vo.CgvMemberVO;
import com.mycgv.vo.SessionVO;
	
	public interface MemberService {
		SessionVO getLoginResult(CgvMemberVO vo); //로그인 처리 메소드
		int getJoinResult(CgvMemberVO vo);  //회원가입 처리 메소드
		ArrayList<CgvMemberVO> getList(int startCount, int endCount); // 관리자용 회원 전체 리스트 메소드
		int getTotalCount();  //전체 회원수
		CgvMemberVO getMemberContent(String id); // 회원 상세정보
	}
