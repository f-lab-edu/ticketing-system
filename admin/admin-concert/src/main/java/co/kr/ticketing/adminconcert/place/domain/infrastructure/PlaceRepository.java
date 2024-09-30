package co.kr.ticketing.adminconcert.place.domain.infrastructure;

import java.util.List;
import java.util.Optional;

import co.kr.ticketing.adminconcert.place.domain.infrastructure.dto.UpdatePlaceDto;
import co.kr.ticketing.adminconcert.place.domain.model.Place;

public interface PlaceRepository {
	Optional<Place> find(long id);

	Optional<Place> findLastVersion(String identifier);

	List<Place> getLastVersionsByPlaceName(String placeName);

	Place create(Place place);

	Place update(long id, UpdatePlaceDto updateDto);
}
