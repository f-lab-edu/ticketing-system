package co.kr.ticketing.memberconcert.concert.domain.model;

import lombok.Builder;

@Builder
public record ConcertSeat(
	Long id,
	String grade,
	int price,
	int columnNum,
	int rowNum,
	int floor,
	ConcertSeatState state
) {
}
