package co.kr.ticketing.memberconcert.concert.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.kr.ticketing.memberconcert.concert.domain.model.ConcertState;
import co.kr.ticketing.memberconcert.concert.repository.entity.ConcertEntity;

public interface ConcertJpaRepository extends JpaRepository<ConcertEntity, Long> {
	Page<ConcertEntity> findByStateIs(Pageable pageable, ConcertState state);
}
