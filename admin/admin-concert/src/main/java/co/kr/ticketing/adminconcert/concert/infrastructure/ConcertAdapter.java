package co.kr.ticketing.adminconcert.concert.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.adminconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.adminconcert.concert.domain.infrastructure.ConcertRepository;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.repository.ConcertJpaRepository;
import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertEntity;
import co.kr.ticketing.adminconcert.place.repository.PlaceJpaRepository;
import co.kr.ticketing.adminconcert.place.repository.entity.PlaceEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertAdapter implements ConcertRepository {
	ConcertJpaRepository concertJpaRepository;
	PlaceJpaRepository placeJpaRepository;

	@Override
	public Optional<Concert> find(Long id) {
		return concertJpaRepository.findById(id).map(ConcertEntity::toModel);
	}

	@Override
	@Transactional
	public long create(Concert concert) {
		PlaceEntity placeEntity = placeJpaRepository.findById(concert.place().id())
			.orElseThrow(() -> new ResourceNotFoundException("place", concert.place().id()));

		return concertJpaRepository.save(ConcertEntity.from(concert, placeEntity)).getId();
	}
}
