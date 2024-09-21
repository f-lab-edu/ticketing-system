package co.kr.ticketing.adminconcert.place.controller.response;

import java.util.List;

import co.kr.ticketing.adminconcert.place.domain.model.PlaceVersion;
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
		public static PlaceResponse from(PlaceVersion version) {
			return PlaceResponse.builder()
				.id(version.getPlace().getId())
				.identifier(version.getIdentifier())
				.name(version.getPlace().getName())
				.build();
		}
	}

	public static GetPlaceResponse from(List<PlaceVersion> places) {
		return GetPlaceResponse.builder()
			.places(places.stream().map(PlaceResponse::from).toList())
			.build();
	}
}
