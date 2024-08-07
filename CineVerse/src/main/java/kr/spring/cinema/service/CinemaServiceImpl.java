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
import kr.spring.seat.vo.SeatVO;

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
	public List<MovieVO> getMoviesByCinema(long c_num) {
		return cinemaMapper.getMoviesByCinema(c_num);
	}

	@Override
	public List<MovieTimeVO> selectMovieTimeList(long c_num, long m_code, String mt_date) {
		return cinemaMapper.selectMovieTimeList(c_num, m_code, mt_date); 
	}

	@Override
	public List<MovieTimeVO> selectAllInfoList(long mt_num) {
		return cinemaMapper.selectAllInfoList(mt_num);
	}

	@Override
	public List<SeatVO> selectSeatList(long mt_num) {
		return cinemaMapper.selectSeatList(mt_num);
	}

	@Override
	public List<MovieTimeVO> movieReserveList(long c_num) {
		return cinemaMapper.movieReserveList(c_num);
	}

	@Override
    public List<MovieTimeVO> getMoviesSortedByReservationRate(long c_num) {
        return cinemaMapper.getMoviesSortedByReservationRate(c_num);
    }

    @Override
    public List<MovieTimeVO> getMoviesSortedByName(long c_num) {
        return cinemaMapper.getMoviesSortedByName(c_num);
    }

    @Override
    public List<MovieTimeVO> getDefaultMovieList(long c_num) {
        return cinemaMapper.getDefaultMovieList(c_num); // 최신순 정렬
    }
	
	@Override
	public List<MovieVO> insertTimeMovieList() {
		return cinemaMapper.insertTimeMovieList();
	}

	@Override
	public List<CinemaVO> insertTimeCinemaList() {
		return cinemaMapper.insertTimeCinemaList();
	}

	@Override
	public List<TheaterVO> insertTimeTheaterList(long c_num) {
		return cinemaMapper.insertTimeTheaterList(c_num);
	}

	@Override
	public boolean checkOverlap(MovieTimeVO movieTimeVO) {
		int count = cinemaMapper.checkOverlap(movieTimeVO);
	    return count > 0;  // 겹치는 시간이 있으면 true 반환
	}

	@Override
	public List<MovieVO> showMovieList() {
		return cinemaMapper.showMovieList();
	}

	@Override
	public List<MovieVO> showMovieList2() {
		return cinemaMapper.showMovieList2();
	}
	
	@Override
	public List<MovieVO> showMovieList3() {
		return cinemaMapper.showMovieList3();
	}
	
	@Override
	public List<MovieTimeVO> showMovieTimeList(long m_code, String mt_date, String c_location) {
		return cinemaMapper.showMovieTimeList(m_code, mt_date, c_location);
	}

	@Override
	public List<MovieVO> getMovieRankMain() {
		return cinemaMapper.getMovieRankMain();
	}

	@Override
	public Integer getBookedSeats(long mt_num) {
		return cinemaMapper.bookingcount(mt_num);
	}

	@Override
	public Integer getAvailableSeats(long mt_num) {
		Integer totalSeats = 96; // 전체 좌석 수 (고정값 또는 데이터베이스에서 가져올 수 있음)
        Integer bookedSeats = getBookedSeats(mt_num);
        return totalSeats - (bookedSeats != null ? bookedSeats : 0);
	}

	@Override
	public List<MovieTimeVO> controllMovieTime(Map<String, Object> map) {
		return cinemaMapper.controllMovieTime(map);
	}

	@Override
	public Integer controllMovieTimeRowCount(Map<String, Object> map) {
		return cinemaMapper.controllMovieTimeRowCount(map);
	}

	@Override
	public void updateMovieTime(MovieTimeVO movietime) {
		cinemaMapper.updateMovieTime(movietime);
	}

	@Override
	public void deleteMovieTime(long mt_num) {
		cinemaMapper.deleteMovieTime(mt_num);
	}

	@Override
	public MovieTimeVO getMovieTimeById(long mt_num) {
		return cinemaMapper.getMovieTimeById(mt_num);
	}

	
	
	
	
	
	
	/*
	 * @Override public Integer bookingcount(long mt_num) { return
	 * cinemaMapper.bookingcount(mt_num); }
	 */

 
	
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
