package co.kr.ticketing.memberconcert.concert.usecase.reader;

import org.springframework.stereotype.Service;

import co.kr.ticketing.memberconcert.concert.controller.response.GetRoundStatusResponse;
import co.kr.ticketing.memberconcert.concert.domain.model.ConcertSeatState;
import co.kr.ticketing.memberconcert.concert.domain.model.Round;
import co.kr.ticketing.memberconcert.concert.service.ConcertSeatService;
import co.kr.ticketing.memberconcert.concert.service.RoundService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetRoundStatusUseCase {
	RoundService roundService;
	ConcertSeatService concertSeatService;

	public GetRoundStatusResponse execute(Long id, int roundId) {
		Round round = roundService.getByConcertIdAndSequenceNumber(id, roundId);

		int count = concertSeatService.count(id, round.sequenceNumber(), ConcertSeatState.EMPTY);

		return GetRoundStatusResponse.from(round, count);
	}
}
