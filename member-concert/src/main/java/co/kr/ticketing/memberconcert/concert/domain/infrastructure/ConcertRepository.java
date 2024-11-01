package co.kr.ticketing.memberconcert.concert.domain.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.kr.ticketing.memberconcert.concert.domain.model.Concert;

public interface ConcertRepository {
	Page<Concert> getList(Pageable pageable);
}
