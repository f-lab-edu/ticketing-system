package co.kr.ticketing.memberconcert.concert.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import co.kr.ticketing.memberconcert.place.domain.model.Place;
import lombok.Builder;

@Builder
public record Concert(
	Long id,
	String name,
	String detailInfo,
	int runningTime,
	ConcertState state,
	LocalDateTime ticketingStartTime,
	List<Round> rounds,
	Place place,
	List<ConcertSeat> seats
) {
}
