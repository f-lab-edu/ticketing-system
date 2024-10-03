package co.kr.ticketing.adminconcert.concert.controller.request;

import java.time.LocalDateTime;
import java.util.List;

import co.kr.ticketing.adminconcert.concert.service.dto.CreateConcertDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateConcertRequest(
	@NotBlank
	String name,
	String detailInfo,
	@NotNull
	Integer runningHour,
	@NotNull
	Integer runningMinute,
	LocalDateTime ticketingStartTime,
	LocalDateTime openTime,
	@NotEmpty
	List<CreateConcertRoundRequest> rounds,
	@NotNull
	Long placeId,
	List<CreateConcertSeatRequest> seats
) {
	public record CreateConcertRoundRequest(
		LocalDateTime startDateTime
	) {
		public CreateConcertDto.CreateRoundDto toDto() {
			return CreateConcertDto.CreateRoundDto.builder()
				.startDateTime(startDateTime)
				.build();
		}
	}

	public record CreateConcertSeatRequest(
		String grade,
		int price,
		int columnNum,
		int rowNum,
		int floor
	) {
		public CreateConcertDto.CreateConcertSeatDto toDto() {
			return CreateConcertDto.CreateConcertSeatDto.builder()
				.grade(grade)
				.price(price)
				.columnNum(columnNum)
				.rowNum(rowNum)
				.floor(floor)
				.build();
		}
	}

	public CreateConcertDto toDto() {
		return CreateConcertDto.builder()
			.name(name)
			.detailInfo(detailInfo)
			.runningHour(runningHour)
			.runningMinute(runningMinute)
			.ticketingStartTime(ticketingStartTime)
			.openTime(openTime)
			.rounds(rounds.stream().map(CreateConcertRoundRequest::toDto).toList())
			.placeId(placeId)
			.seats(seats.stream().map(CreateConcertSeatRequest::toDto).toList())
			.build();
	}
}