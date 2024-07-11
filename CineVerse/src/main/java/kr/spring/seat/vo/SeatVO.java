package kr.spring.seat.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SeatVO {
	private Long seat_num;			//좌석번호
	private String seat_rank;		//좌석 상태(1: 일반석 2: VIP석 3: 장애인석 4: 커플석)
	private String seat_row;		//좌석 가로행(A, B, C...)
	private int seat_column;		//좌석 세로열 (1, 2, 3,...)
	private Long c_num;				//영화관 번호
}