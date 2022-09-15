package com.spring.service;

import java.util.ArrayList;

import com.mycgv.vo.CgvMovieVO;

public interface MovieService {

	int getTotalCount(); // ��ȭ��� �ο� ��
	ArrayList<CgvMovieVO> getList(int startCount, int endCount); // ��ȭ��� ����Ʈ
	CgvMovieVO getContent(String mid); //��ȭ��� �󼼺���
	int getRegistResult(CgvMovieVO vo); //��ȭ��� �۾��� ó��
	String getSelectMid();
	int getRegistFile(CgvMovieVO vo);
	
	
}
