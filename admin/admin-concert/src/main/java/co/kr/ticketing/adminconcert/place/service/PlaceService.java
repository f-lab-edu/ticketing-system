package co.kr.ticketing.adminconcert.place.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.adminconcert.common.exception.DuplicateException;
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
	UUIDGenerator uuidGenerator;

	public Place getLastVersion(String identifier) {
		return placeRepository.findTopByIdentifierOrderByVersionDesc(identifier)
			.orElseThrow(() -> new ResourceNotFoundException("place", identifier));
	}

	public List<Place> getLastVersionsByPlaceName(String placeName) {
		return placeRepository.findByNameContainsAndLastIsTrue(placeName);
	}

	@Transactional
	public long create(Place place) {
		return placeRepository.save(Place.getNewPlace(place, uuidGenerator)).getId();
	}

	@Transactional
	public long add(Place place) {
		try {
			Place lastPlace = getLastVersion(place.getIdentifier());

			return placeRepository.save(place.getNextPlace(lastPlace)).getId();
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateException("정보가 수정됐습니다. 확인 후 다시 작성 해주세요");
		}
	}
}