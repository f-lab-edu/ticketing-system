package co.kr.ticketing.adminconcert.place.usecase;

import org.springframework.stereotype.Service;

import co.kr.ticketing.adminconcert.place.controller.request.AddPlaceRequest;
import co.kr.ticketing.adminconcert.place.service.PlaceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddPlaceUseCase {
	PlaceService placeService;

	public long execute(AddPlaceRequest request) {
		if (request.identifier() == null) {
			return placeService.create(request.toModel());
		} else {
			return placeService.add(request.toModel());
		}
	}
}
