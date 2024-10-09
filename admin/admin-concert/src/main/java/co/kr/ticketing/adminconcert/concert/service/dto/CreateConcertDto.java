package co.kr.ticketing.adminconcert.concert.service.dto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import co.kr.ticketing.adminconcert.common.exception.BadRequestException;
import co.kr.ticketing.adminconcert.concert.controller.response.ConcertErrorResponseCode;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertSeat;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertSeatState;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertState;
import co.kr.ticketing.adminconcert.concert.domain.model.Round;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import co.kr.ticketing.adminconcert.place.domain.model.Seat;
import lombok.Builder;

@Builder
public record CreateConcertDto(
	String name,
	String detailInfo,
	Integer runningTime,
	LocalDateTime ticketingStartTime,
	LocalDateTime openTime,
	List<CreateRoundDto> rounds,
	Long placeId,
	List<CreateConcertSeatDto> seats
) {
	@Builder
	public record CreateRoundDto(
		LocalDateTime startDateTime
	) {
		public Round toModel() {
			return Round.builder()
				.startDateTime(startDateTime)
				.build();
		}
	}

	@Builder
	public record CreateConcertSeatDto(
		String grade,
		int price,
		int columnNum,
		int rowNum,
		int floor
	) {
		public ConcertSeat toModel() {
			return ConcertSeat.builder()
				.grade(grade)
				.price(price)
				.columnNum(columnNum)
				.rowNum(rowNum)
				.floor(floor)
				.state(ConcertSeatState.EMPTY)
				.build();
		}
	}

	public Concert toModel(List<Seat> placeSeats) {
		validate(placeSeats);

		return Concert.builder()
			.name(name)
			.detailInfo(detailInfo)
			.runningTime(runningTime)
			.state(ConcertState.READY)
			.ticketingStartTime(ticketingStartTime)
			.openTime(openTime)
			.rounds(rounds.stream().map(CreateRoundDto::toModel).toList())
			.place(Place.builder()
				.id(placeId)
				.seats(Collections.emptyList())
				.build())
			.seats(seats.stream().map(CreateConcertSeatDto::toModel).toList())
			.build();
	}

	private void validate(List<Seat> placeSeats) {
		if (runningTime < 0) {
			throw new BadRequestException(ConcertErrorResponseCode.CREATE_RUNNING_TIME_ERROR.name(),
				"러닝타임은 0 이상만 가능합니다");
		}

		if (ticketingStartTime != null && ticketingStartTime.isBefore(LocalDateTime.now())) {
			throw new BadRequestException(ConcertErrorResponseCode.CREATE_TICKETING_START_TIME_ERROR.name(),
				"티케팅 시작 시간은 현재 시간 이후로 설정해주세요");
		}

		if (openTime != null && openTime.isBefore(LocalDateTime.now())) {
			throw new BadRequestException(ConcertErrorResponseCode.CREATE_OPEN_TIME_ERROR.name(),
				"공연 오픈일은 현재 시간 이후로 설정해주세요");
		}

		if (openTime != null && ticketingStartTime != null && ticketingStartTime.isBefore(openTime)) {
			throw new BadRequestException(ConcertErrorResponseCode.CREATE_TICKETING_START_TIME_ERROR.name(),
				"티케팅 시작 시간은 오픈 시간 이후로 설정해주세요");
		}

		if (rounds.stream().anyMatch(round -> round.startDateTime.isBefore(LocalDateTime.now()))) {
			throw new BadRequestException(ConcertErrorResponseCode.CREATE_ROUNDS_ERROR.name(),
				"공연 시간은 현재 시간 이후로 설정해주세요");
		}

		if (seats.size() != placeSeats.size()) {
			throw new BadRequestException(ConcertErrorResponseCode.CREATE_SEATS_ERROR.name(),
				"좌석정보가 선택한 장소의 좌석정보와 일치하지 않습니다");
		}
	}
}
