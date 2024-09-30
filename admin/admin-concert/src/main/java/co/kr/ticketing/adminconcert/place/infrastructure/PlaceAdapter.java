package co.kr.ticketing.adminconcert.place.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import co.kr.ticketing.adminconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.adminconcert.place.domain.infrastructure.PlaceRepository;
import co.kr.ticketing.adminconcert.place.domain.infrastructure.dto.UpdatePlaceDto;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import co.kr.ticketing.adminconcert.place.repository.PlaceJpaRepository;
import co.kr.ticketing.adminconcert.place.repository.entity.PlaceEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlaceAdapter implements PlaceRepository {
	PlaceJpaRepository placeJpaRepository;

	@Override
	public Optional<Place> find(long id) {
		return placeJpaRepository.findById(id).map(PlaceEntity::toModel);
	}

	@Override
	public Optional<Place> findLastVersion(String identifier) {
		return placeJpaRepository.findTopByIdentifierOrderByVersionDesc(identifier)
			.map(PlaceEntity::toModel);
	}

	@Override
	public List<Place> getLastVersionsByPlaceName(String placeName) {
		return placeJpaRepository.findByNameContainsAndLastIsTrue(placeName)
			.stream()
			.map(PlaceEntity::toModel)
			.toList();
	}

	@Override
	public Place create(Place place) {
		return placeJpaRepository.save(PlaceEntity.from(place)).toModel();
	}

	@Override
	public Place update(long id, UpdatePlaceDto updateDto) {
		PlaceEntity placeEntity = placeJpaRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("place", id));

		placeEntity.update(updateDto);

		return placeEntity.toModel();
	}
}
