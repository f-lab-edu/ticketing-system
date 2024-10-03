package co.kr.ticketing.adminconcert.concert.domain.infrastructure;

import java.util.Optional;

import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.service.dto.UpdateConcertDto;

public interface ConcertRepository {
	Optional<Concert> find(Long id);

	long create(Concert concert);

	long update(long id, UpdateConcertDto updateDto);
}
