package co.kr.ticketing.adminconcert.place.controller.response;

import java.util.List;

import co.kr.ticketing.adminconcert.place.domain.model.Place;
import lombok.Builder;

@Builder
public record GetPlaceResponse(
	List<PlaceResponse> places
) {
	@Builder
	public record PlaceResponse(
		Long id,
		String identifier,
		String name
	) {
		public static PlaceResponse from(Place place) {
			return PlaceResponse.builder()
				.id(place.id())
				.identifier(place.identifier())
				.name(place.name())
				.build();
		}
	}

	public static GetPlaceResponse from(List<Place> places) {
		return GetPlaceResponse.builder()
			.places(places.stream().map(PlaceResponse::from).toList())
			.build();
	}
}
