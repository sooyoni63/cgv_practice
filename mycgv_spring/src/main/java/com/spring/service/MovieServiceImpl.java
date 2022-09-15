package com.spring.service;

import java.util.ArrayList;

import com.mycgv.dao.CgvMovieDAO;
import com.mycgv.vo.CgvMovieVO;

public class MovieServiceImpl implements MovieService {

	
	/*
	 * ��ȭ��� �ο� ��
	 */
	@Override
	public int getTotalCount() {
		CgvMovieDAO dao = new CgvMovieDAO();
		int dbCount = dao.totalCount();
		return dbCount;
	}
	
	/*
	 * ��ȭ��� ����Ʈ
	 */
	@Override
	public ArrayList<CgvMovieVO> getList(int startCount, int endCount){
		CgvMovieDAO dao = new CgvMovieDAO();
		ArrayList<CgvMovieVO> list = dao.select(startCount, endCount);
		return list;
	}
	
	/*
	 * ��ȭ��� ������
	 */
	@Override
	public CgvMovieVO getContent(String mid) {
		CgvMovieDAO dao = new CgvMovieDAO();
		CgvMovieVO vo = dao.select(mid);
		return vo;
	}
	
	/*
	 * ��ȭ��� �۾���
	 */
	
	@Override
	public int getRegistResult(CgvMovieVO vo) {
		CgvMovieDAO dao = new CgvMovieDAO();
		int result = dao.insert(vo);
		return result;
	}
	
	/*
	 * Mid ��������
	 */
	
	public String getSelectMid() {
		CgvMovieDAO dao = new CgvMovieDAO();
		String mid = dao.selectMid();
		return mid;
	}
	
	/*
	 * �̹��� ���
	 */
	
	public int getRegistFile(CgvMovieVO vo) {
		CgvMovieDAO dao = new CgvMovieDAO();
		int result2 = dao.insert_file(vo);
		return result2;
	}
}
