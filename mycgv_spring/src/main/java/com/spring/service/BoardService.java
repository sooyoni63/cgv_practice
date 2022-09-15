package com.spring.service;

import java.util.ArrayList;

import com.mycgv.vo.CgvBoardVO;

public interface BoardService {

	ArrayList<CgvBoardVO> getList(int startCount, int endCount); // �Խñ� ��ü ����Ʈ 
	int getTotalCount(); //�Խñ� ��ü �� 
	int getWriteResult(CgvBoardVO vo); //�Խñ� ����
	CgvBoardVO getContent(String bid); //�Խñ� �󼼺���
	void getUpdateHits(String bid); //�Խñ� ��ȸ�� ������Ʈ
	int getUpdateResult(CgvBoardVO vo); //�Խñ� ����
	int getDeleteResult(String bid); //�Խñ� ����
	
}
