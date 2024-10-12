package co.kr.ticketing.adminconcert.concert.domain.infrastructure;

import java.util.List;

import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.domain.model.Round;

public interface RoundRepository {
	void create(long concertId, List<Round> rounds);

	void update(Concert concert);
}
