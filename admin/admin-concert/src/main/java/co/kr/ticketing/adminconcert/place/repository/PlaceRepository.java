package co.kr.ticketing.adminconcert.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.kr.ticketing.adminconcert.place.domain.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
