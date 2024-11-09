package co.kr.ticketing.memberconcert.concert.service;

import org.springframework.stereotype.Service;

import co.kr.ticketing.memberconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.memberconcert.concert.domain.infrastructure.RoundRepository;
import co.kr.ticketing.memberconcert.concert.domain.model.Round;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoundService {
	RoundRepository roundRepository;

	public Round getByConcertIdAndSequenceNumber(long concertId, int sequenceNumber) {
		return roundRepository.findByConcertIdAndSequenceNumber(concertId, sequenceNumber)
			.orElseThrow(() -> new ResourceNotFoundException(
				"round: concertId:" + concertId + " sequenceNumber:" + sequenceNumber));
	}
}
