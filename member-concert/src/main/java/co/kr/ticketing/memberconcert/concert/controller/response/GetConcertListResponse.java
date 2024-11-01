package co.kr.ticketing.memberconcert.concert.controller.response;

import java.time.LocalDateTime;

import co.kr.ticketing.memberconcert.concert.domain.model.Concert;
import lombok.Builder;

@Builder
public record GetConcertListResponse(
	Long id,
	String name,
	String detailInfo,
	Integer runningTime,
	LocalDateTime ticketingStartTime,
	String placeName
) {
	public static GetConcertListResponse from(Concert concert) {
		return GetConcertListResponse.builder()
			.id(concert.id())
			.name(concert.name())
			.detailInfo(concert.detailInfo())
			.runningTime(concert.runningTime())
			.ticketingStartTime(concert.ticketingStartTime())
			.placeName(concert.place().name())
			.build();
	}
}
