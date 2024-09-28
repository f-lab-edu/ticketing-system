package co.kr.ticketing.adminconcert.place.controller.request;

import java.util.List;

import co.kr.ticketing.adminconcert.place.domain.model.Place;
import co.kr.ticketing.adminconcert.place.domain.model.Seat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AddPlaceRequest(
	@NotBlank
	String name,
	@NotBlank
	String address,
	@Valid
	@NotEmpty
	List<SeatRequest> seats,
	String identifier
) {
	public record SeatRequest(
		@NotNull
		Integer columnNum,
		@NotNull
		Integer rowNum,
		@NotNull
		Integer floor
	) {
		public Seat toModel() {
			return Seat.builder()
				.columnNum(columnNum)
				.rowNum(rowNum)
				.floor(floor)
				.build();
		}
	}

	public Place toModel() {
		return Place.builder()
			.name(name)
			.address(address)
			.identifier(identifier)
			.last(true)
			.seats(seats.stream().map(SeatRequest::toModel).toList())
			.build();
	}
}
