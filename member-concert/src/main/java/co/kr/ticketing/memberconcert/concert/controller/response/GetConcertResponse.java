package co.kr.ticketing.memberconcert.concert.controller.response;

import java.time.LocalDateTime;
import java.util.List;

import co.kr.ticketing.memberconcert.concert.domain.model.Concert;
import co.kr.ticketing.memberconcert.concert.domain.model.Round;
import lombok.Builder;

@Builder
public record GetConcertResponse(
	Long id,
	String name,
	String detailInfo,
	int runningTime,
	LocalDateTime ticketingStartTime,
	List<RoundResponse> rounds,
	String placeName
) {
	@Builder
	public record RoundResponse(
		Long id,
		LocalDateTime startDateTime
	) {
		public static RoundResponse from(Round round) {
			return RoundResponse.builder()
				.id(round.id())
				.startDateTime(round.startDateTime())
				.build();
		}
	}

	public static GetConcertResponse from(Concert concert) {
		return GetConcertResponse.builder()
			.id(concert.id())
			.name(concert.name())
			.detailInfo(concert.detailInfo())
			.runningTime(concert.runningTime())
			.ticketingStartTime(concert.ticketingStartTime())
			.rounds(concert.rounds().stream().map(RoundResponse::from).toList())
			.placeName(concert.place().name())
			.build();
	}
}
