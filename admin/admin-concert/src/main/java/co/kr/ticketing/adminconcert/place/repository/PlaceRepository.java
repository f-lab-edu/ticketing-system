package co.kr.ticketing.adminconcert.place.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.kr.ticketing.adminconcert.place.domain.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
	Optional<Place> findTopByIdentifierOrderByVersionDesc(String identifier);

	List<Place> findByNameContainsAndLastIsTrue(String name);
}
