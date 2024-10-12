package co.kr.ticketing.adminconcert.concert.domain.infrastructure;

import java.util.List;

import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertSeat;

public interface ConcertSeatRepository {
	void create(long concertId, List<ConcertSeat> concertSeats);

	void update(Concert concert);
}
