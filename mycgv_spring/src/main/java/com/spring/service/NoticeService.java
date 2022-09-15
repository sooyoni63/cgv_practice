package com.spring.service;

import java.util.ArrayList;

import com.mycgv.vo.CgvNoticeVO;

public interface NoticeService {

	int getTotalCount(); // �������� �ο� ��
	ArrayList<CgvNoticeVO> getList(int startCount, int endCount); //�������� ��ü ����Ʈ
	CgvNoticeVO getContent(String nid); //�������� �󼼺���
	void getUpdateHits(String nid); //��ȸ��
	int getWriteResult(CgvNoticeVO vo); //�������� �۾��� ó��
	int getUpdateResult(CgvNoticeVO vo); //�������� �����ϱ�
	int getDeleteResult(String nid); //�������� ����
	
	
}
