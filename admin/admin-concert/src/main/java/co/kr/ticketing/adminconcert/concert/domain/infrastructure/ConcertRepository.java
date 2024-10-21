package co.kr.ticketing.adminconcert.concert.domain.infrastructure;

import java.util.List;
import java.util.Optional;

import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.service.dto.GetConcertListVo;

public interface ConcertRepository {
	Optional<Concert> find(Long id);

	List<Concert> getList(GetConcertListVo getListVo);

	List<Concert> getListToClose();

	long create(Concert concert);

	long update(Concert concert);

	long setOpenTime(Concert concert);

	long setTicketingStartTime(Concert concert);

	long updatePlace(Concert concert);

	long changeState(Concert concert);
}
