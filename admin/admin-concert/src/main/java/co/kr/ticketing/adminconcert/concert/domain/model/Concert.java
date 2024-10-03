package co.kr.ticketing.adminconcert.concert.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import co.kr.ticketing.adminconcert.place.domain.model.Place;
import lombok.Builder;

@Builder
public record Concert(
	Long id,
	String name,
	String detailInfo,
	int runningHour,
	int runningMinute,
	CONCERT_STATE state,
	LocalDateTime ticketingStartTime,
	LocalDateTime lastRunningEndTime,
	LocalDateTime openTime,
	List<Round> rounds,
	Place place,
	List<ConcertSeat> seats
) {
	public boolean isPossibleUpdate() {
		return !state.equals(CONCERT_STATE.CLOSE);
	}
}
