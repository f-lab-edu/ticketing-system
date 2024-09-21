package co.kr.ticketing.adminconcert.place.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.adminconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import co.kr.ticketing.adminconcert.place.repository.PlaceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlaceService {
	PlaceRepository placeRepository;

	public Place getById(long id) {
		return placeRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("concert place", id));
	}

	@Transactional
	public long add(Place place) {
		return placeRepository.save(place).getId();
	}
}