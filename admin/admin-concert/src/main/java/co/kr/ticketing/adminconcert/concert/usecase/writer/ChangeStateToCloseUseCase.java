package co.kr.ticketing.adminconcert.concert.usecase.writer;

import org.springframework.stereotype.Service;

import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertState;
import co.kr.ticketing.adminconcert.concert.service.ConcertService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChangeStateToCloseUseCase {
	ConcertService concertService;

	public long execute(long id) {
		Concert concert = concertService.get(id);
		return concertService.changeState(concert, ConcertState.CLOSE);
	}
}
