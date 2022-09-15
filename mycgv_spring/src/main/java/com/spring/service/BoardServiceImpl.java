package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycgv.dao.CgvBoardDAO;
import com.mycgv.vo.CgvBoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private CgvBoardDAO boardDAO;

	/*
	 * 게시판 전체 리스트
	 */
	@Override
	public ArrayList<CgvBoardVO> getList(int startCount, int endCount){		
		return boardDAO.select(startCount, endCount);
	}

	
	/*
	 * 전체 페이지 수
	 */
	@Override
	public int getTotalCount() {
		return boardDAO.totalCount();
	}
	
	/*
	 * 게시글 쓰기
	 */
	@Override
	public int getWriteResult(CgvBoardVO vo) {
		return boardDAO.insert(vo);
	}
	
	/*
	 * 게시글 상세보기
	 */
	@Override
	public CgvBoardVO getContent(String bid) {
		return boardDAO.select(bid);
	}

	
	/*
	 * 조회수 업데이트
	 */
	@Override
	public void getUpdateHits(String bid) {
		boardDAO.updateHits(bid);
	}
	
	/*
	 * 게시글 업데이트
	 */
	
	@Override
	public int getUpdateResult(CgvBoardVO vo) {
		return boardDAO.update(vo);
	}
	
	/*
	 * 게시글 삭제
	 */
	
	public int getDeleteResult(String bid) {
		return boardDAO.delete(bid);
	}

}
