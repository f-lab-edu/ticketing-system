package co.kr.ticketing.adminconcert.concert.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.adminconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.adminconcert.concert.domain.infrastructure.ConcertRepository;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.repository.ConcertJpaRepository;
import co.kr.ticketing.adminconcert.concert.repository.ConcertQdslRepository;
import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertEntity;
import co.kr.ticketing.adminconcert.concert.service.dto.GetConcertListVo;
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
	ConcertQdslRepository concertQdslRepository;
	PlaceJpaRepository placeJpaRepository;

	@Override
	public Optional<Concert> find(Long id) {
		return concertJpaRepository.findById(id).map(ConcertEntity::toModel);
	}

	@Override
	public List<Concert> getList(GetConcertListVo getListVo) {
		return concertQdslRepository.getList(getListVo).stream().map(ConcertEntity::toModel).toList();
	}

	@Override
	public List<Concert> getListToClose() {
		return concertQdslRepository.getListToClose().stream().map(ConcertEntity::toModel).toList();
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
	public long update(Concert concert) {
		ConcertEntity concertEntity = concertJpaRepository.findById(concert.id())
			.orElseThrow(() -> new ResourceNotFoundException("concert", concert.id()));

		concertEntity.update(concert);

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
	public long updatePlace(Concert concert) {
		ConcertEntity concertEntity = concertJpaRepository.findById(concert.id())
			.orElseThrow(() -> new ResourceNotFoundException("concert", concert.id()));

		PlaceEntity placeEntity = placeJpaRepository.findById(concert.place().id())
			.orElseThrow(() -> new ResourceNotFoundException("place", concert.place().id()));

		concertEntity.updatePlace(placeEntity);

		return concertEntity.getId();
	}

	@Override
	@Transactional
	public long changeState(Concert concert) {
		ConcertEntity concertEntity = concertJpaRepository.findById(concert.id())
			.orElseThrow(() -> new ResourceNotFoundException("concert", concert.id()));

		concertEntity.setState(concert.state());

		return concertEntity.getId();
	}
}
