package co.kr.ticketing.memberconcert.concert.domain.infrastructure;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.kr.ticketing.memberconcert.concert.domain.model.Concert;

public interface ConcertReadRepository {
	Page<Concert> getPage(Pageable pageable);

	Optional<Concert> find(long id);
}
