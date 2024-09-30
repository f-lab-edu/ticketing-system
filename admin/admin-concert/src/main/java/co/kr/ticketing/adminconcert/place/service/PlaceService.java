package co.kr.ticketing.adminconcert.place.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.adminconcert.common.exception.DuplicateException;
import co.kr.ticketing.adminconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.adminconcert.place.domain.infrastructure.PlaceRepository;
import co.kr.ticketing.adminconcert.place.domain.infrastructure.dto.UpdatePlaceDto;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlaceService {
	PlaceRepository placeRepository;
	UUIDGenerator uuidGenerator;

	public Place get(long id) {
		return placeRepository.find(id)
			.orElseThrow(() -> new ResourceNotFoundException("place", id));
	}

	public Place getLastVersion(String identifier) {
		return placeRepository.findLastVersion(identifier)
			.orElseThrow(() -> new ResourceNotFoundException("place", identifier));
	}

	public List<Place> getLastVersionsByPlaceName(String placeName) {
		return placeRepository.getLastVersionsByPlaceName(placeName);
	}

	@Transactional
	public long create(Place place) {
		return placeRepository.create(Place.getNewPlace(place, uuidGenerator)).id();
	}

	@Transactional
	public long add(Place place) {
		try {
			Place lastPlace = getLastVersion(place.identifier());

			updateToNotLast(lastPlace.id());

			return placeRepository.create(place.getNextPlace(lastPlace)).id();
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateException("정보가 수정됐습니다. 확인 후 다시 작성 해주세요");
		}
	}

	public long updateToNotLast(long id) {
		Place place = get(id);

		return placeRepository.update(id, UpdatePlaceDto.builder()
				.name(place.name())
				.address(place.address())
				.identifier(place.identifier())
				.version(place.version())
				.last(false)
				.build())
			.id();
	}
}