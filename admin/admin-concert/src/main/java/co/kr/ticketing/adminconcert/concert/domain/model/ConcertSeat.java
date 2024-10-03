package co.kr.ticketing.adminconcert.concert.domain.model;

import lombok.Builder;

@Builder
public record ConcertSeat(
	Long id,
	String grade,
	int price,
	int columnNum,
	int rowNum,
	int floor,
	CONCERT_SEAT_STATE state
) {
}
