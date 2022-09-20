	package com.spring.service;
	
	import java.util.ArrayList;

import com.mycgv.vo.CgvMemberVO;
import com.mycgv.vo.SessionVO;
	
	public interface MemberService {
		SessionVO getLoginResult(CgvMemberVO vo); //�α��� ó�� �޼ҵ�
		int getJoinResult(CgvMemberVO vo);  //ȸ������ ó�� �޼ҵ�
		ArrayList<CgvMemberVO> getList(int startCount, int endCount); // �����ڿ� ȸ�� ��ü ����Ʈ �޼ҵ�
		int getTotalCount();  //��ü ȸ����
		CgvMemberVO getMemberContent(String id); // ȸ�� ������
	}
