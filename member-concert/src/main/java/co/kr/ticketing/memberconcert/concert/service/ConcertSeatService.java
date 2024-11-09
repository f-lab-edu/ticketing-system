package co.kr.ticketing.memberconcert.concert.service;

import org.springframework.stereotype.Service;

import co.kr.ticketing.memberconcert.concert.domain.infrastructure.ConcertSeatRepository;
import co.kr.ticketing.memberconcert.concert.domain.model.ConcertSeatState;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertSeatService {
	ConcertSeatRepository concertSeatRepository;

	public int count(Long concertId, int sequenceNumber, ConcertSeatState state) {
		return concertSeatRepository.count(concertId, sequenceNumber, state);
	}
}
