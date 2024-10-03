package co.kr.ticketing.adminconcert.concert.domain.infrastructure;

import java.util.Optional;

import co.kr.ticketing.adminconcert.concert.domain.model.Concert;

public interface ConcertRepository {
	Optional<Concert> find(Long id);

	long create(Concert concert);
}
