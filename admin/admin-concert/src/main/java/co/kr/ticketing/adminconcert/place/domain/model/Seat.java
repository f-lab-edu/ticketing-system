package co.kr.ticketing.adminconcert.place.domain.model;

import lombok.Builder;

@Builder
public record Seat(
	Long id,
	int columnNum,
	int rowNum,
	int floor
) {
}
