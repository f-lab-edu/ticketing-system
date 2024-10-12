package co.kr.ticketing.adminconcert.concert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.kr.ticketing.adminconcert.concert.repository.entity.RoundEntity;

@Repository
public interface RoundJpaRepository extends JpaRepository<RoundEntity, Long> {
	void deleteAllByConcertEntityId(long concertEntityId);
}
