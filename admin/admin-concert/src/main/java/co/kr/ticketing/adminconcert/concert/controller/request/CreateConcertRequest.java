package co.kr.ticketing.adminconcert.concert.controller.request;

import java.time.LocalDateTime;
import java.util.List;

import co.kr.ticketing.adminconcert.concert.service.dto.CreateConcertVo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateConcertRequest(
	@NotBlank
	String name,
	String detailInfo,
	@NotNull
	Integer runningTime,
	LocalDateTime ticketingStartTime,
	LocalDateTime openTime,
	@Valid
	@NotEmpty
	List<CreateConcertRoundRequest> rounds,
	@NotNull
	Long placeId,
	@Valid
	@NotEmpty
	List<CreateConcertSeatRequest> seats
) {
	public record CreateConcertRoundRequest(
		@NotNull
		LocalDateTime startDateTime
	) {
		public CreateConcertVo.CreateRoundVo toCreateVo() {
			return CreateConcertVo.CreateRoundVo.builder()
				.startDateTime(startDateTime)
				.build();
		}
	}

	public record CreateConcertSeatRequest(
		@NotBlank
		String grade,
		@NotNull
		Integer price,
		@NotNull
		Integer columnNum,
		@NotNull
		Integer rowNum,
		@NotNull
		Integer floor
	) {
		public CreateConcertVo.CreateConcertSeatVo toCreateVo() {
			return CreateConcertVo.CreateConcertSeatVo.builder()
				.grade(grade)
				.price(price)
				.columnNum(columnNum)
				.rowNum(rowNum)
				.floor(floor)
				.build();
		}
	}

	public CreateConcertVo toCreateVo() {
		return CreateConcertVo.builder()
			.name(name)
			.detailInfo(detailInfo)
			.runningTime(runningTime)
			.ticketingStartTime(ticketingStartTime)
			.openTime(openTime)
			.rounds(rounds.stream().map(CreateConcertRoundRequest::toCreateVo).toList())
			.placeId(placeId)
			.seats(seats.stream().map(CreateConcertSeatRequest::toCreateVo).toList())
			.build();
	}
}
