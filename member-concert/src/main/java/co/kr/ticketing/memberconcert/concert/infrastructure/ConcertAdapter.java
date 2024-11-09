package co.kr.ticketing.memberconcert.concert.infrastructure;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.memberconcert.concert.domain.infrastructure.ConcertRepository;
import co.kr.ticketing.memberconcert.concert.domain.model.Concert;
import co.kr.ticketing.memberconcert.concert.domain.model.ConcertState;
import co.kr.ticketing.memberconcert.concert.repository.ConcertJpaRepository;
import co.kr.ticketing.memberconcert.concert.repository.entity.ConcertEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertAdapter implements ConcertRepository {
	ConcertJpaRepository concertJpaRepository;

	@Override
	public Page<Concert> getList(Pageable pageable) {
		return concertJpaRepository.findByStateIs(pageable, ConcertState.OPEN).map(ConcertEntity::toModel);
	}

	@Override
	public Optional<Concert> find(long id) {
		return concertJpaRepository.findById(id).map(ConcertEntity::toModel);
	}
}
