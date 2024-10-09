package co.kr.ticketing.adminconcert.concert.domain.infrastructure;

import java.util.Optional;

import co.kr.ticketing.adminconcert.concert.domain.model.Concert;

public interface ConcertRepository {
	Optional<Concert> find(Long id);

	long create(Concert concert);

	long update(Concert concert);

	long setOpenTime(Concert concert);

	long setTicketingStartTime(Concert concert);

	long modifyRounds(Concert concert);

	long updatePlace(Concert concert);
}
