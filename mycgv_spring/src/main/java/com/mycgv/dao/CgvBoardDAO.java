package com.mycgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv.vo.CgvBoardVO;

@Repository
public class CgvBoardDAO{
	
	//sqlSession 가져오기
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * totalCount : 전체 로우수 출력
	 */
	public int totalCount() {	
		return sqlSession.selectOne("mapper.board.total_count");
	}
	
	/**
	 * delete : 게시글 삭제
	 */
	public int delete(String bid) {		
		return sqlSession.delete("mapper.board.delete", bid);
	}
	
	
	/**
	 * update : 게시글 수정 
	 */
	public int update(CgvBoardVO vo) {
		return sqlSession.update("mapper.board.update", vo);
	}
	
	/**
	 * updateHits : 조회수 업데이트
	 */
	public int updateHits(String bid) {	
		return sqlSession.update("mapper.board.updatehits", bid);
	}
	
	/**
	 * select : 게시글 상세 보기
	 */
	public CgvBoardVO select(String bid) {
		return sqlSession.selectOne("mapper.board.content", bid);
	}
	
	
	/**
	 * select : 게시글 전체 리스트(페이징처리)
	 */
	public ArrayList<CgvBoardVO> select(int startCount, int endCount){
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("start", startCount);
		param.put("end", endCount);

		List<CgvBoardVO> list = sqlSession.selectList("mapper.board.listAll", param);
		
		return (ArrayList<CgvBoardVO>)list;
	}
	
	
	/**
	 * insert : 게시글 등록
	 */
	public int insert(CgvBoardVO vo) {
		return sqlSession.insert("mapper.board.insert", vo);
	}
}
