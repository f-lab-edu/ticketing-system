package co.kr.ticketing.adminconcert.concert.domain.infrastructure;

import co.kr.ticketing.adminconcert.concert.domain.model.Concert;

public interface ConcertRepository {
	long create(Concert concert);
}
