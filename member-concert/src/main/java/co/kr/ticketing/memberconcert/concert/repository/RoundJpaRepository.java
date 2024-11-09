package co.kr.ticketing.memberconcert.concert.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.kr.ticketing.memberconcert.concert.repository.entity.RoundEntity;

public interface RoundJpaRepository extends JpaRepository<RoundEntity, Long> {
	Optional<RoundEntity> findByConcertEntityIdAndSequenceNumber(Long concertId, int sequenceNumber);
}
