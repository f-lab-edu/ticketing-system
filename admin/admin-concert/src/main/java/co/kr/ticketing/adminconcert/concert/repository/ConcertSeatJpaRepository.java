package co.kr.ticketing.adminconcert.concert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertSeatEntity;

@Repository
public interface ConcertSeatJpaRepository extends JpaRepository<ConcertSeatEntity, Long> {
	void deleteAllByConcertEntityId(long concertEntityId);
}
