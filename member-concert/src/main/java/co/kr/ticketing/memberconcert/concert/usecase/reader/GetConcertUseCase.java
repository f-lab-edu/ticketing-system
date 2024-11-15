package co.kr.ticketing.memberconcert.concert.usecase.reader;

import org.springframework.stereotype.Service;

import co.kr.ticketing.memberconcert.concert.controller.response.GetConcertResponse;
import co.kr.ticketing.memberconcert.concert.domain.model.Concert;
import co.kr.ticketing.memberconcert.concert.service.ConcertService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetConcertUseCase {
	ConcertService concertService;

	public GetConcertResponse execute(Long id) {
		Concert concert = concertService.getById(id);

		return GetConcertResponse.from(concert);
	}
}
