package co.kr.ticketing.adminconcert.place.usecase;

import org.springframework.stereotype.Service;

import co.kr.ticketing.adminconcert.place.controller.request.AddPlaceRequest;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import co.kr.ticketing.adminconcert.place.domain.model.PlaceVersion;
import co.kr.ticketing.adminconcert.place.service.PlaceService;
import co.kr.ticketing.adminconcert.place.service.PlaceVersionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddPlaceUseCase {
	PlaceService placeService;
	PlaceVersionService placeVersionService;

	public long execute(AddPlaceRequest request) {
		Place newPlace = placeService.getById(placeService.add(request.toModel()));

		if (request.identifier() == null) {
			placeVersionService.createNewVersion(newPlace);
		} else {
			PlaceVersion lastVersion = placeVersionService.getLastVersion(request.identifier());
			placeVersionService.addVersion(lastVersion, newPlace);
		}

		return newPlace.getId();
	}
}
