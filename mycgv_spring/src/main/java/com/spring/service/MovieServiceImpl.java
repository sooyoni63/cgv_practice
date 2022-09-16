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
	 * ��ȭ��� �ο� ��
	 */
	@Override
	public int getTotalCount() {
		return movieDAO.totalCount();
	}
	
	/*
	 * ��ȭ��� ����Ʈ
	 */
	@Override
	public ArrayList<CgvMovieVO> getList(int startCount, int endCount){
		return movieDAO.select(startCount, endCount);
	}
	
	/*
	 * ��ȭ��� ������
	 */
	@Override
	public CgvMovieVO getContent(String mid) {
		return movieDAO.select(mid);
	}
	
	/*
	 * ��ȭ��� �۾���
	 */
	
	@Override
	public int getRegistResult(CgvMovieVO vo) {
		return movieDAO.insert(vo);
	}
	
	/*
	 * Mid ��������
	 */
	
	public String getSelectMid() {
		return movieDAO.selectMid();
	}
	
	/*
	 * �̹��� ���
	 */
	
	public int getRegistFile(CgvMovieVO vo) {
		return movieDAO.insert_file(vo);
	}
}
