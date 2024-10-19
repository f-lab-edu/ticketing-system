package co.kr.ticketing.adminconcert.concert.service.dto;

import java.time.LocalDateTime;

import co.kr.ticketing.adminconcert.concert.domain.model.ConcertState;
import lombok.Builder;

@Builder
public record GetConcertListVo(
	String name,
	String placeName,
	ConcertState state,
	LocalDateTime openTime,
	Integer searchOpenTimeMinutes
) {
	public LocalDateTime getSearchStartOpenTime() {
		return openTime.minusMinutes(searchOpenTimeMinutes);
	}
}
