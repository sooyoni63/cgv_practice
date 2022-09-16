package com.mycgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv.vo.CgvMovieVO;

@Repository
public class CgvMovieDAO{
	
	//sqlSession 가져오기
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace="mapper.movie";
	
	/**
	 * select : 영화 상세 정보
	 */
	public CgvMovieVO select(String mid) {		
		return sqlSession.selectOne("mapper.movie.content", mid);
	}
	
	
	/**
	 * select : 전체 영화 리스트
	 */
	public ArrayList<CgvMovieVO> select(int startCount, int endCount){
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<CgvMovieVO> list = sqlSession.selectList("mapper.movie.listAll", param);
		
		return (ArrayList<CgvMovieVO>)list;
	}
	
	
	/**
	 * totalCount : 전체 로우수 출력
	 */
	public int totalCount() {	
		return sqlSession.selectOne(namespace+".totalcount");
	}
	
	/**
	 * insert_file : 영화이미지 등록 처리
	 */
	public int insert_file(CgvMovieVO vo) {	
		return sqlSession.insert("mapper.movie.insertfile", vo);
	}
	
	
	/**
	 * selectMid : mid 가져오기
	 */
	public String selectMid() {
		return sqlSession.selectOne("mapper.movie.selectmid");
	}
	
	/**
	 * insert : 영화등록 기능
	 */
	public int insert(CgvMovieVO vo) {		
		return sqlSession.insert("mapper.movie.write", vo);
	}
}
