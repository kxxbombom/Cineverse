package kr.spring.support.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import kr.spring.support.vo.ConsultVO;

public interface SupportService {
	public List<ConsultVO> selectConsultList(Map<String,Object> map);
	public Integer selectRowCount(Map<String,Object> map);
	public void insertConsult(ConsultVO consult);
	@Select("SELECT * FROM consult JOIN member USING(mem_num) LEFT OUTER JOIN member_detail USING(mem_num) WHERE consult_num=#{consult_num}")
	public ConsultVO selectConsult(Long consult_num);
	
	@Delete("DELETE FROM consult WHERE consult_num=#{consult_num}")
	public void deleteConsult(Long consult_num);
}