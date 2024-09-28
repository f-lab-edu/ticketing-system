package co.kr.ticketing.adminconcert.place.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import co.kr.ticketing.adminconcert.place.controller.request.GetPlaceRequest;
import co.kr.ticketing.adminconcert.place.controller.response.GetPlaceResponse;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import co.kr.ticketing.adminconcert.place.service.PlaceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetPlaceUseCase {
	PlaceService placeService;

	public GetPlaceResponse execute(GetPlaceRequest request) {
		List<Place> places = placeService.getLastVersionsByPlaceName(request.name());

		return GetPlaceResponse.from(places);
	}
}
