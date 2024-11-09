package co.kr.ticketing.memberconcert.concert.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.memberconcert.concert.domain.infrastructure.ConcertSeatRepository;
import co.kr.ticketing.memberconcert.concert.domain.model.ConcertSeatState;
import co.kr.ticketing.memberconcert.concert.repository.ConcertSeatJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertSeatAdapter implements ConcertSeatRepository {
	ConcertSeatJpaRepository concertSeatJpaRepository;

	@Override
	public int count(Long concertId, int sequenceNumber, ConcertSeatState state) {
		return concertSeatJpaRepository.countByConcertEntityIdAndSequenceNumberAndState(concertId, sequenceNumber,
			state);
	}
}
