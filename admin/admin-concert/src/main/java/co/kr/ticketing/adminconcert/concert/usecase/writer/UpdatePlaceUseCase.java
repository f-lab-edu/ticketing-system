package co.kr.ticketing.adminconcert.concert.usecase.writer;

import org.springframework.stereotype.Service;

import co.kr.ticketing.adminconcert.concert.controller.request.UpdatePlaceRequest;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.service.ConcertService;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import co.kr.ticketing.adminconcert.place.service.PlaceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdatePlaceUseCase {
	ConcertService concertService;
	PlaceService placeService;

	public long execute(Long id, UpdatePlaceRequest request) {
		Concert concert = concertService.get(id);
		Place place = placeService.get(request.placeId());

		return concertService.updatePlace(concert, place, request.toSeatModel());
	}
}
