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
	 * �Խ��� ��ü ����Ʈ
	 */
	@Override
	public ArrayList<CgvBoardVO> getList(int startCount, int endCount){		
		return boardDAO.select(startCount, endCount);
	}

	
	/*
	 * ��ü ������ ��
	 */
	@Override
	public int getTotalCount() {
		return boardDAO.totalCount();
	}
	
	/*
	 * �Խñ� ����
	 */
	@Override
	public int getWriteResult(CgvBoardVO vo) {
		return boardDAO.insert(vo);
	}
	
	/*
	 * �Խñ� �󼼺���
	 */
	@Override
	public CgvBoardVO getContent(String bid) {
		return boardDAO.select(bid);
	}

	
	/*
	 * ��ȸ�� ������Ʈ
	 */
	@Override
	public void getUpdateHits(String bid) {
		boardDAO.updateHits(bid);
	}
	
	/*
	 * �Խñ� ������Ʈ
	 */
	
	@Override
	public int getUpdateResult(CgvBoardVO vo) {
		return boardDAO.update(vo);
	}
	
	/*
	 * �Խñ� ����
	 */
	
	public int getDeleteResult(String bid) {
		return boardDAO.delete(bid);
	}

}
