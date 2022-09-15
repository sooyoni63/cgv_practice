package com.spring.service;

import java.util.ArrayList;

import com.mycgv.dao.CgvMovieDAO;
import com.mycgv.vo.CgvMovieVO;

public class MovieServiceImpl implements MovieService {

	
	/*
	 * 영화등록 로우 수
	 */
	@Override
	public int getTotalCount() {
		CgvMovieDAO dao = new CgvMovieDAO();
		int dbCount = dao.totalCount();
		return dbCount;
	}
	
	/*
	 * 영화등록 리스트
	 */
	@Override
	public ArrayList<CgvMovieVO> getList(int startCount, int endCount){
		CgvMovieDAO dao = new CgvMovieDAO();
		ArrayList<CgvMovieVO> list = dao.select(startCount, endCount);
		return list;
	}
	
	/*
	 * 영화등록 상세정보
	 */
	@Override
	public CgvMovieVO getContent(String mid) {
		CgvMovieDAO dao = new CgvMovieDAO();
		CgvMovieVO vo = dao.select(mid);
		return vo;
	}
	
	/*
	 * 영화등록 글쓰기
	 */
	
	@Override
	public int getRegistResult(CgvMovieVO vo) {
		CgvMovieDAO dao = new CgvMovieDAO();
		int result = dao.insert(vo);
		return result;
	}
	
	/*
	 * Mid 가져오기
	 */
	
	public String getSelectMid() {
		CgvMovieDAO dao = new CgvMovieDAO();
		String mid = dao.selectMid();
		return mid;
	}
	
	/*
	 * 이미지 등록
	 */
	
	public int getRegistFile(CgvMovieVO vo) {
		CgvMovieDAO dao = new CgvMovieDAO();
		int result2 = dao.insert_file(vo);
		return result2;
	}
}
