package co.kr.ticketing.memberconcert.concert.domain.infrastructure;

import co.kr.ticketing.memberconcert.concert.domain.model.ConcertSeatState;

public interface ConcertSeatRepository {
	int count(Long concertId, int sequenceNumber, ConcertSeatState state);
}
