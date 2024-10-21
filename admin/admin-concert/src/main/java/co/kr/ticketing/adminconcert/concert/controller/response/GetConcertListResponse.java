package co.kr.ticketing.adminconcert.concert.controller.response;

import java.time.LocalDateTime;
import java.util.List;

import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertState;
import lombok.Builder;

@Builder
public record GetConcertListResponse(
	List<ConcertResponse> concerts
) {
	@Builder
	public record ConcertResponse(
		Long id,
		String name,
		ConcertState state,
		LocalDateTime openTime,
		LocalDateTime ticketingStartTime,
		LocalDateTime lastRunningEndTime,
		String placeName
	) {
		public static ConcertResponse from(Concert concert) {
			return ConcertResponse.builder()
				.id(concert.id())
				.name(concert.name())
				.state(concert.state())
				.openTime(concert.openTime())
				.ticketingStartTime(concert.ticketingStartTime())
				.lastRunningEndTime(concert.lastRunningEndTime())
				.placeName(concert.place().name())
				.build();
		}
	}

	public static GetConcertListResponse from(List<Concert> concerts) {
		return GetConcertListResponse.builder()
			.concerts(concerts.stream().map(ConcertResponse::from).toList())
			.build();
	}
}
