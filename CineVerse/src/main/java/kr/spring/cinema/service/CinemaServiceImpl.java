package kr.spring.cinema.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.cinema.dao.CinemaMapper;
import kr.spring.cinema.vo.CinemaVO;
import kr.spring.cinema.vo.TheaterVO;
import kr.spring.movie.vo.MovieTimeVO;
import kr.spring.movie.vo.MovieVO;

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


	@Override
	public List<TheaterVO> selectTheaterListByCinemaNum(long c_num) {
		return cinemaMapper.selectTheaterListByCinemaNum(c_num);
	}

	@Override
	public Integer selectTheaterCountByCinema(long c_num) {
		return cinemaMapper.selectTheaterCountByCinema(c_num);
	}

	@Override
	public List<MovieTimeVO> selectMovieTimeListByCinemaNum(long c_num) {
		return cinemaMapper.selectMovieTimeListByCinemaNum(c_num);
	}

	@Override
	public Integer selectMovieTimeCount(Map<String, Object> map) {
		return cinemaMapper.selectMovieTimeCount(map);
	}

	@Override
	public List<MovieVO> getMoviesByCinema(long c_num) {
		return cinemaMapper.getMoviesByCinema(c_num);
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
