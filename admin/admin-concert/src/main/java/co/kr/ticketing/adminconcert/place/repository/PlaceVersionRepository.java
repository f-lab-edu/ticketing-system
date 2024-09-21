package co.kr.ticketing.adminconcert.place.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.kr.ticketing.adminconcert.place.domain.model.PlaceVersion;

@Repository
public interface PlaceVersionRepository extends JpaRepository<PlaceVersion, Long> {
	Optional<PlaceVersion> findTopByIdentifierOrderByVersionDesc(String identifier);

	List<PlaceVersion> findByPlaceNameContainsAndLastIsTrue(String name);
}
