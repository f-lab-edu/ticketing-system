package co.kr.ticketing.adminconcert.concert.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.adminconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.adminconcert.concert.domain.infrastructure.ConcertRepository;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.repository.ConcertJpaRepository;
import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertEntity;
import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertSeatEntity;
import co.kr.ticketing.adminconcert.concert.repository.entity.RoundEntity;
import co.kr.ticketing.adminconcert.concert.service.dto.UpdateConcertDto;
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

	@Override
	@Transactional
	public long update(long id, UpdateConcertDto updateDto) {
		ConcertEntity concertEntity = concertJpaRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("concert", id));

		concertEntity.update(updateDto);

		return concertEntity.getId();
	}

	@Override
	@Transactional
	public long setOpenTime(Concert concert) {
		ConcertEntity concertEntity = concertJpaRepository.findById(concert.id())
			.orElseThrow(() -> new ResourceNotFoundException("concert", concert.id()));

		concertEntity.setOpenTime(concert.openTime());

		return concertEntity.getId();
	}

	@Override
	@Transactional
	public long setTicketingStartTime(Concert concert) {
		ConcertEntity concertEntity = concertJpaRepository.findById(concert.id())
			.orElseThrow(() -> new ResourceNotFoundException("concert", concert.id()));

		concertEntity.setTicketingStartTime(concert.ticketingStartTime());

		return concertEntity.getId();
	}

	@Override
	@Transactional
	public long modifyRounds(Concert concert) {
		ConcertEntity concertEntity = concertJpaRepository.findById(concert.id())
			.orElseThrow(() -> new ResourceNotFoundException("concert", concert.id()));

		concertEntity.setRoundEntities(concert.rounds().stream().map(RoundEntity::from).toList());

		return concertEntity.getId();
	}

	@Override
	@Transactional
	public long updatePlace(Concert concert) {
		ConcertEntity concertEntity = concertJpaRepository.findById(concert.id())
			.orElseThrow(() -> new ResourceNotFoundException("concert", concert.id()));

		PlaceEntity placeEntity = placeJpaRepository.findById(concert.place().id())
			.orElseThrow(() -> new ResourceNotFoundException("place", concert.place().id()));

		concertEntity.updatePlace(placeEntity, concert.seats().stream().map(ConcertSeatEntity::from).toList());

		return concertEntity.getId();
	}
}
