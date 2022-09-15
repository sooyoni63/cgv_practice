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
	
	//sqlSession ��������
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * totalCount : ��ü �ο�� ���
	 */
	public int totalCount() {	
		return sqlSession.selectOne("mapper.board.total_count");
	}
	
	/**
	 * delete : �Խñ� ����
	 */
	public int delete(String bid) {		
		return sqlSession.delete("mapper.board.delete", bid);
	}
	
	
	/**
	 * update : �Խñ� ���� 
	 */
	public int update(CgvBoardVO vo) {
		return sqlSession.update("mapper.board.update", vo);
	}
	
	/**
	 * updateHits : ��ȸ�� ������Ʈ
	 */
	public int updateHits(String bid) {	
		return sqlSession.update("mapper.board.updatehits", bid);
	}
	
	/**
	 * select : �Խñ� �� ����
	 */
	public CgvBoardVO select(String bid) {
		return sqlSession.selectOne("mapper.board.content", bid);
	}
	
	
	/**
	 * select : �Խñ� ��ü ����Ʈ(����¡ó��)
	 */
	public ArrayList<CgvBoardVO> select(int startCount, int endCount){
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("start", startCount);
		param.put("end", endCount);

		List<CgvBoardVO> list = sqlSession.selectList("mapper.board.listAll", param);
		
		return (ArrayList<CgvBoardVO>)list;
	}
	
	
	/**
	 * insert : �Խñ� ���
	 */
	public int insert(CgvBoardVO vo) {
		return sqlSession.insert("mapper.board.insert", vo);
	}
}
