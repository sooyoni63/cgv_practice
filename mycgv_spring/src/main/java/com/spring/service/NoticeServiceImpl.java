package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycgv.dao.CgvNoticeDAO;
import com.mycgv.vo.CgvNoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private CgvNoticeDAO noticeDAO;
	
	/*
	 * �������� ��ü �ο� ��
	 */
	@Override
	public int getTotalCount() {
		return noticeDAO.totalCount();
	}
	
	/*
	 * �������� ��ü ����Ʈ
	 */
	@Override
	public ArrayList<CgvNoticeVO> getList(int startCount, int endCount){
		return noticeDAO.select(startCount,endCount);
	}
	
	/*
	 * �������� �󼼺���
	 */
	@Override
	public CgvNoticeVO getContent(String nid) {
		return noticeDAO.select(nid);
	}
	
	/*
	 * ��ȸ�� ������Ʈ
	 */
	@Override
	public void getUpdateHits(String nid) {
		noticeDAO.updateHits(nid);
	}
	
	/*
	 * �۾��� �Է�
	 */
	
	@Override
	public int getWriteResult(CgvNoticeVO vo) {
		return noticeDAO.insert(vo);
	}
	
	/*
	 * �۾��� ����
	 */
	@Override
	public int getUpdateResult(CgvNoticeVO vo) {
		return noticeDAO.update(vo);
	}
	
	/*
	 * �۾��� ����
	 */
	@Override
	public int getDeleteResult(String nid) {
		return noticeDAO.delete(nid);
	}
	
}
