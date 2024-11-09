package co.kr.ticketing.memberconcert.concert.domain.infrastructure;

import java.util.Optional;

import co.kr.ticketing.memberconcert.concert.domain.model.Round;

public interface RoundRepository {
	Optional<Round> findByConcertIdAndSequenceNumber(Long concertId, int sequenceNumber);
}
