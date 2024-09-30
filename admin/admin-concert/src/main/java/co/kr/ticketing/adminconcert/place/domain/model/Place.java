package co.kr.ticketing.adminconcert.place.domain.model;

import java.util.List;

import co.kr.ticketing.adminconcert.place.domain.IdentifierGenerator;
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
	public static Place getNewPlace(Place place, IdentifierGenerator generator) {
		return Place.builder()
			.name(place.name())
			.address(place.address())
			.identifier(generator.generate())
			.version(0L)
			.last(true)
			.seats(List.copyOf(place.seats()))
			.build();
	}

	public Place getNextPlace(Place beforePlace) {
		return Place.builder()
			.name(name)
			.address(address)
			.identifier(identifier)
			.version(beforePlace.getNextVersion())
			.last(true)
			.seats(List.copyOf(seats()))
			.build();
	}

	public Long getNextVersion() {
		return this.version + 1;
	}
}
