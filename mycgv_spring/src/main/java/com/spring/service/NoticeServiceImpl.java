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
	 * 공지사항 전체 로우 수
	 */
	@Override
	public int getTotalCount() {
		return noticeDAO.totalCount();
	}
	
	/*
	 * 공지사항 전체 리스트
	 */
	@Override
	public ArrayList<CgvNoticeVO> getList(int startCount, int endCount){
		return noticeDAO.select(startCount,endCount);
	}
	
	/*
	 * 공지사항 상세보기
	 */
	@Override
	public CgvNoticeVO getContent(String nid) {
		return noticeDAO.select(nid);
	}
	
	/*
	 * 조회수 업데이트
	 */
	@Override
	public void getUpdateHits(String nid) {
		noticeDAO.updateHits(nid);
	}
	
	/*
	 * 글쓰기 입력
	 */
	
	@Override
	public int getWriteResult(CgvNoticeVO vo) {
		return noticeDAO.insert(vo);
	}
	
	/*
	 * 글쓰기 수정
	 */
	@Override
	public int getUpdateResult(CgvNoticeVO vo) {
		return noticeDAO.update(vo);
	}
	
	/*
	 * 글쓰기 삭제
	 */
	@Override
	public int getDeleteResult(String nid) {
		return noticeDAO.delete(nid);
	}
	
}
