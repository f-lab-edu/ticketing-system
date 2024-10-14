package co.kr.ticketing.adminconcert.concert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertSeatEntity;

@Repository
public interface ConcertSeatJpaRepository extends JpaRepository<ConcertSeatEntity, Long> {
	List<ConcertSeatEntity> getByConcertEntityId(long concertId);

	void deleteAllByConcertEntityId(long concertEntityId);
}
