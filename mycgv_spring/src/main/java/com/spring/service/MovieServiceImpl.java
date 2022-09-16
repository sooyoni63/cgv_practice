package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycgv.dao.CgvMovieDAO;
import com.mycgv.vo.CgvMovieVO;

@Service
public class MovieServiceImpl implements MovieService {

	
	@Autowired
	private CgvMovieDAO movieDAO;
	
	/*
	 * 영화등록 로우 수
	 */
	@Override
	public int getTotalCount() {
		return movieDAO.totalCount();
	}
	
	/*
	 * 영화등록 리스트
	 */
	@Override
	public ArrayList<CgvMovieVO> getList(int startCount, int endCount){
		return movieDAO.select(startCount, endCount);
	}
	
	/*
	 * 영화등록 상세정보
	 */
	@Override
	public CgvMovieVO getContent(String mid) {
		return movieDAO.select(mid);
	}
	
	/*
	 * 영화등록 글쓰기
	 */
	
	@Override
	public int getRegistResult(CgvMovieVO vo) {
		return movieDAO.insert(vo);
	}
	
	/*
	 * Mid 가져오기
	 */
	
	public String getSelectMid() {
		return movieDAO.selectMid();
	}
	
	/*
	 * 이미지 등록
	 */
	
	public int getRegistFile(CgvMovieVO vo) {
		return movieDAO.insert_file(vo);
	}
}
