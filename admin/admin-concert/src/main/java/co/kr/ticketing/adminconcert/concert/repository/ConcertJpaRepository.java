package co.kr.ticketing.adminconcert.concert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertEntity;

@Repository
public interface ConcertJpaRepository extends JpaRepository<ConcertEntity, Long> {
}
