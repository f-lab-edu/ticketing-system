package co.kr.ticketing.adminconcert.place.domain.infrastructure.dto;

import lombok.Builder;

@Builder
public record UpdatePlaceDto(
	String name,
	String address,
	String identifier,
	Long version,
	boolean last
) {
}
