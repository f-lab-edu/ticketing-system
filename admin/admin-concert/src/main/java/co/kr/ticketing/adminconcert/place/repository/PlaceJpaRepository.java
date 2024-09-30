package co.kr.ticketing.adminconcert.place.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.kr.ticketing.adminconcert.place.repository.entity.PlaceEntity;

@Repository
public interface PlaceJpaRepository extends JpaRepository<PlaceEntity, Long> {
	Optional<PlaceEntity> findTopByIdentifierOrderByVersionDesc(String identifier);

	List<PlaceEntity> findByNameContainsAndLastIsTrue(String name);
}
