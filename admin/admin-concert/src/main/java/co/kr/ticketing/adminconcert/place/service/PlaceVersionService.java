package co.kr.ticketing.adminconcert.place.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import co.kr.ticketing.adminconcert.common.exception.DuplicateException;
import co.kr.ticketing.adminconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import co.kr.ticketing.adminconcert.place.domain.model.PlaceVersion;
import co.kr.ticketing.adminconcert.place.repository.PlaceVersionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlaceVersionService {
	PlaceVersionRepository placeVersionRepository;
	UUIDGenerator uuidGenerator;

	public PlaceVersion getLastVersion(String identifier) {
		return placeVersionRepository.findTopByIdentifierOrderByVersionDesc(identifier)
			.orElseThrow(() -> new ResourceNotFoundException("version", identifier));
	}

	public List<PlaceVersion> getLastVersionsByPlaceName(String placeName) {
		return placeVersionRepository.findByPlaceNameContainsAndLastIsTrue(placeName);
	}

	public long createNewVersion(Place place) {
		PlaceVersion newVersion = PlaceVersion.newVersion(place, uuidGenerator);
		return placeVersionRepository.save(newVersion).getId();
	}

	public long addVersion(PlaceVersion version, Place place) {
		long id;
		try {
			PlaceVersion placeVersion = version.nextVersion(place);

			id = placeVersionRepository.save(placeVersion).getId();
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateException("정보가 수정됐습니다. 확인 후 다시 작성 해주세요");
		}
		return id;
	}
}
