package com.spring.service;

import java.util.ArrayList;

import com.mycgv.vo.CgvBoardVO;

public interface BoardService {

	ArrayList<CgvBoardVO> getList(int startCount, int endCount); // 게시글 전체 리스트 
	int getTotalCount(); //게시글 전체 수 
	int getWriteResult(CgvBoardVO vo); //게시글 쓰기
	CgvBoardVO getContent(String bid); //게시글 상세보기
	void getUpdateHits(String bid); //게시글 조회수 업데이트
	int getUpdateResult(CgvBoardVO vo); //게시글 수정
	int getDeleteResult(String bid); //게시글 삭제
	
}
