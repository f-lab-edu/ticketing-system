package co.kr.ticketing.adminconcert.concert.usecase.writer;

import org.springframework.stereotype.Service;

import co.kr.ticketing.adminconcert.concert.controller.request.ModifyConcertSeatsRequest;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.service.ConcertService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ModifyConcertSeatsUseCase {
	ConcertService concertService;

	public long execute(Long id, ModifyConcertSeatsRequest request) {
		Concert concert = concertService.get(id);

		return concertService.modifySeats(concert, request.toVo());
	}
}
