package co.kr.ticketing.adminconcert.concert.controller.response;

import java.time.LocalDateTime;
import java.util.List;

import co.kr.ticketing.adminconcert.concert.domain.model.CONCERT_SEAT_STATE;
import co.kr.ticketing.adminconcert.concert.domain.model.CONCERT_STATE;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertSeat;
import co.kr.ticketing.adminconcert.concert.domain.model.Round;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import lombok.Builder;

@Builder
public record GetConcertResponse(
	Long id,
	String name,
	String detailInfo,
	int runningHour,
	int runningMinute,
	CONCERT_STATE state,
	LocalDateTime ticketingStartTime,
	LocalDateTime openTime,
	List<RoundResponse> rounds,
	PlaceResponse place,
	List<ConcertSeatResponse> seats
) {
	@Builder
	public record RoundResponse(
		LocalDateTime startDateTime
	) {
		public static RoundResponse from(Round round) {
			return RoundResponse.builder()
				.startDateTime(round.startDateTime())
				.build();
		}
	}

	@Builder
	public record PlaceResponse(
		Long id,
		String name,
		String address,
		String identifier
	) {
		public static PlaceResponse from(Place place) {
			return PlaceResponse.builder()
				.id(place.id())
				.name(place.name())
				.address(place.address())
				.identifier(place.identifier())
				.build();
		}
	}

	@Builder
	public record ConcertSeatResponse(
		Long id,
		String grade,
		int price,
		int columnNum,
		int rowNum,
		int floor,
		CONCERT_SEAT_STATE state
	) {
		public static ConcertSeatResponse from(ConcertSeat concertSeat) {
			return ConcertSeatResponse.builder()
				.id(concertSeat.id())
				.grade(concertSeat.grade())
				.price(concertSeat.price())
				.columnNum(concertSeat.columnNum())
				.rowNum(concertSeat.rowNum())
				.floor(concertSeat.floor())
				.state(concertSeat.state())
				.build();
		}
	}

	public static GetConcertResponse from(Concert concert) {
		return GetConcertResponse.builder()
			.id(concert.id())
			.name(concert.name())
			.detailInfo(concert.detailInfo())
			.runningHour(concert.runningHour())
			.runningMinute(concert.runningMinute())
			.state(concert.state())
			.ticketingStartTime(concert.ticketingStartTime())
			.openTime(concert.openTime())
			.rounds(concert.rounds().stream().map(RoundResponse::from).toList())
			.place(PlaceResponse.from(concert.place()))
			.seats(concert.seats().stream().map(ConcertSeatResponse::from).toList())
			.build();
	}
}
