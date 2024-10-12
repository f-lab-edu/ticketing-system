package co.kr.ticketing.adminconcert.concert.infrastructure;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.adminconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.adminconcert.concert.domain.infrastructure.RoundRepository;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.domain.model.Round;
import co.kr.ticketing.adminconcert.concert.repository.ConcertJpaRepository;
import co.kr.ticketing.adminconcert.concert.repository.RoundJpaRepository;
import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertEntity;
import co.kr.ticketing.adminconcert.concert.repository.entity.RoundEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoundAdapter implements RoundRepository {
	ConcertJpaRepository concertJpaRepository;
	RoundJpaRepository roundJpaRepository;

	@Override
	public void create(long concertId, List<Round> rounds) {
		ConcertEntity concertEntity = concertJpaRepository.findById(concertId)
			.orElseThrow(() -> new ResourceNotFoundException("concert", concertId));

		roundJpaRepository.saveAll(
			rounds.stream().map(round -> RoundEntity.from(concertEntity, round)).toList()
		);
	}

	@Override
	@Transactional
	public void update(Concert concert) {
		ConcertEntity concertEntity = concertJpaRepository.findById(concert.id())
			.orElseThrow(() -> new ResourceNotFoundException("concert", concert.id()));

		roundJpaRepository.deleteAllByConcertEntityId(concert.id());

		create(concertEntity.getId(), concert.rounds());
	}
}
