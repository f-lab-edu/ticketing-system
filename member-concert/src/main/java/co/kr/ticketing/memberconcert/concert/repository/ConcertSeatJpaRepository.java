package co.kr.ticketing.memberconcert.concert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.kr.ticketing.memberconcert.concert.domain.model.ConcertSeatState;
import co.kr.ticketing.memberconcert.concert.repository.entity.ConcertSeatEntity;

public interface ConcertSeatJpaRepository extends JpaRepository<ConcertSeatEntity, Long> {
	int countByConcertEntityIdAndSequenceNumberAndState(Long concertId, int sequenceNumber, ConcertSeatState state);
}
