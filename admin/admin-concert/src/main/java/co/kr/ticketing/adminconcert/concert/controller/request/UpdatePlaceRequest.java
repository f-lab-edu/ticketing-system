package co.kr.ticketing.adminconcert.concert.controller.request;

import java.util.List;

import co.kr.ticketing.adminconcert.concert.domain.model.ConcertSeat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdatePlaceRequest(
	@NotNull
	Long placeId,
	@Valid
	@NotEmpty
	List<ConcertSeatRequest> seats
) {
	public record ConcertSeatRequest(
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
		public ConcertSeat toModel() {
			return ConcertSeat.builder()
				.grade(grade)
				.price(price)
				.columnNum(columnNum)
				.rowNum(rowNum)
				.floor(floor)
				.build();
		}
	}

	public List<ConcertSeat> toSeatModel() {
		return seats.stream().map(ConcertSeatRequest::toModel).toList();
	}
}
