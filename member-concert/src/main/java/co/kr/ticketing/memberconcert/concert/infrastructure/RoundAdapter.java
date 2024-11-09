package co.kr.ticketing.memberconcert.concert.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.memberconcert.concert.domain.infrastructure.RoundRepository;
import co.kr.ticketing.memberconcert.concert.domain.model.Round;
import co.kr.ticketing.memberconcert.concert.repository.RoundJpaRepository;
import co.kr.ticketing.memberconcert.concert.repository.entity.RoundEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoundAdapter implements RoundRepository {
	RoundJpaRepository roundJpaRepository;

	@Override
	public Optional<Round> findByConcertIdAndSequenceNumber(Long concertId, int sequenceNumber) {
		return roundJpaRepository.findByConcertEntityIdAndSequenceNumber(concertId, sequenceNumber)
			.map(RoundEntity::toModel);
	}
}
