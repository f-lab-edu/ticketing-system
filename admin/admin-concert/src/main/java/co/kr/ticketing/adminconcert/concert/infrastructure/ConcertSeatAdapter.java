package co.kr.ticketing.adminconcert.concert.infrastructure;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.adminconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.adminconcert.concert.domain.infrastructure.ConcertSeatRepository;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertSeat;
import co.kr.ticketing.adminconcert.concert.repository.ConcertJpaRepository;
import co.kr.ticketing.adminconcert.concert.repository.ConcertSeatJpaRepository;
import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertEntity;
import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertSeatEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertSeatAdapter implements ConcertSeatRepository {
	ConcertJpaRepository concertJpaRepository;
	ConcertSeatJpaRepository concertSeatJpaRepository;

	@Override
	public void create(long concertId, List<ConcertSeat> concertSeats) {
		ConcertEntity concertEntity = concertJpaRepository.findById(concertId)
			.orElseThrow(() -> new ResourceNotFoundException("concert", concertId));

		concertSeatJpaRepository.saveAll(
			concertSeats.stream()
				.map(round -> ConcertSeatEntity.from(concertEntity, round))
				.toList()
		);
	}

	@Override
	@Transactional
	public void update(Concert concert) {
		ConcertEntity concertEntity = concertJpaRepository.findById(concert.id())
			.orElseThrow(() -> new ResourceNotFoundException("concert", concert.id()));

		concertSeatJpaRepository.deleteAllByConcertEntityId(concertEntity.getId());

		create(concertEntity.getId(), concert.seats());
	}
}
