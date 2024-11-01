package co.kr.ticketing.memberconcert.place.domain.model;

import java.util.List;

import lombok.Builder;

@Builder
public record Place(
	Long id,
	String name,
	String address,
	String identifier,
	Long version,
	boolean last,
	List<Seat> seats
) {
}
