package kr.spring.cinema.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.cinema.dao.CinemaMapper;
import kr.spring.cinema.vo.CinemaVO;

@Service
@Transactional
public class CinemaServiceImpl implements CinemaService{

	@Autowired
	CinemaMapper cinemaMapper;

	@Override
	public List<CinemaVO> selectCinemaList(Map<String, Object> map) {
		return cinemaMapper.selectCinemaList(map);
	}

	@Override
	public Integer selectCinemaCount(Map<String, Object> map) {
		return cinemaMapper.selectCinemaCount(map);
	}

	@Override
	public void insertCinema(CinemaVO cinema) {
		cinemaMapper.insertCinema(cinema); 
	}

	@Override
	public CinemaVO selectCinema(Long c_num) {
		return cinemaMapper.selectCinema(c_num); 
	}

	@Override
	public void updateCinema(CinemaVO cinema) {
		cinemaMapper.updateCinema(cinema);
	} 

	@Override
	public void deleteCinema(Long c_num) {
		cinemaMapper.deleteCinema(c_num); 
	}	
	
	
	
	/*
	 * @Override public List<CinemaVO> selectCinemaList(Integer c_location) { return
	 * cinemaMapper.selectCinemaList(c_location); }
	 * 
	 * @Override public Integer selectCinemaRowCount(Map<String, Object> map) {
	 * return cinemaMapper.selectCinemaRowCount(map); }
	 * 
	 * @Override public void insertCinema(CinemaVO cinema) {
	 * cinemaMapper.insertCinema(cinema); }
	 * 
	 * @Override public CinemaVO selectCinema(Long c_num) { return
	 * cinemaMapper.selectCinema(c_num); }
	 * 
	 * @Override public void updateCinema(CinemaVO cinema) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void deleteCinema(Long c_num) {
	 * cinemaMapper.deleteCinema(c_num); }
	 */

}
