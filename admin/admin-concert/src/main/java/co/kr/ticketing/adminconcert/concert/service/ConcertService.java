package co.kr.ticketing.adminconcert.concert.service;

import org.springframework.stereotype.Service;

import co.kr.ticketing.adminconcert.concert.domain.infrastructure.ConcertRepository;
import co.kr.ticketing.adminconcert.concert.service.dto.CreateConcertDto;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import co.kr.ticketing.adminconcert.place.service.PlaceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertService {
	ConcertRepository concertRepository;
	PlaceService placeService;

	public long create(CreateConcertDto createDto) {
		Place place = placeService.get(createDto.placeId());
		return concertRepository.create(createDto.toModel(place.seats()));
	}
}
