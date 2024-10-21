package co.kr.ticketing.adminconcert.concert.repository;

import static co.kr.ticketing.adminconcert.concert.repository.entity.QConcertEntity.*;
import static co.kr.ticketing.adminconcert.place.repository.entity.QPlaceEntity.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.kr.ticketing.adminconcert.common.repository.QuerydslRepositorySupport;
import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertEntity;
import co.kr.ticketing.adminconcert.concert.service.dto.GetConcertListVo;

@Repository
public class ConcertQdslRepository extends QuerydslRepositorySupport {
	protected ConcertQdslRepository() {
		super(ConcertEntity.class);
	}

	public List<ConcertEntity> getList(GetConcertListVo getListVo) {
		return selectFrom(concertEntity)
			.innerJoin(concertEntity.placeEntity, placeEntity)
			.where(
				getListVo.name() != null ? concertEntity.name.startsWith(getListVo.name()) : null,
				getListVo.placeName() != null ? placeEntity.name.startsWith(getListVo.name()) : null,
				getListVo.state() != null ? concertEntity.state.eq(getListVo.state()) : null,
				getListVo.openTime() != null ?
					concertEntity.openTime.between(getListVo.getSearchStartOpenTime(), getListVo.openTime()) : null
			)
			.limit(100)
			.fetch();
	}

	public List<ConcertEntity> getListToClose() {
		return selectFrom(concertEntity)
			.where(
				concertEntity.lastRunningEndTime.between(
					LocalDateTime.now().minusDays(1).minusMinutes(10),
					LocalDateTime.now().minusDays(1))
			)
			.limit(100)
			.fetch();
	}
}
