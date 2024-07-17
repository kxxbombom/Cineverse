package kr.spring.cinema.service;

import java.util.List;
import java.util.Map;

import kr.spring.cinema.vo.CinemaVO;
import kr.spring.cinema.vo.TheaterVO;
import kr.spring.movie.vo.MovieTimeVO;
import kr.spring.movie.vo.MovieVO;

public interface CinemaService {
	
	//영화관
	public List<CinemaVO> selectCinemaList(Map<String,Object> map);
	public Integer selectCinemaCount(Map<String,Object> map);
	public void insertCinema(CinemaVO cinema);
	public CinemaVO selectCinema(Long c_num);
	public void updateCinema(CinemaVO cinema);
	public void deleteCinema(Long c_num);


	//상영관
	public List<TheaterVO> selectTheaterListByCinemaNum(long c_num);
	public Integer selectTheaterCountByCinema(long c_num);
	
	
	
	//영화시간표 (성공 못함)
	public List<MovieTimeVO> selectMovieTimeListByCinemaNum(long c_num);
	public Integer selectMovieTimeCount(Map<String,Object> map);
	
	//예매하기 부분 영화 리스트 목록 불러오기
	public List<MovieVO> getMoviesByCinema(long c_num);
	
	
	
		//영화관(잠시 보류)
		//public List<CinemaVO> selectCinemaList(Integer c_location);
		//public Integer selectCinemaRowCount(Map<String,Object> map);
		//public void insertCinema(CinemaVO cinema);
		//public CinemaVO selectCinema(Long c_num);
		//public void updateCinema(CinemaVO cinema);
		//public void deleteCinema(Long c_num);
}
