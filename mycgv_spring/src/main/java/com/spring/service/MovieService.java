package com.spring.service;

import java.util.ArrayList;

import com.mycgv.vo.CgvMovieVO;

public interface MovieService {

	int getTotalCount(); // 영화등록 로우 수
	ArrayList<CgvMovieVO> getList(int startCount, int endCount); // 영화등록 리스트
	CgvMovieVO getContent(String mid); //영화등록 상세보기
	int getRegistResult(CgvMovieVO vo); //영화등록 글쓰기 처리
	String getSelectMid();
	int getRegistFile(CgvMovieVO vo);
	
	
}
