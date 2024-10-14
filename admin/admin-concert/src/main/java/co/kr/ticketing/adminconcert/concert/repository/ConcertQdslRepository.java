package co.kr.ticketing.adminconcert.concert.repository;

import static co.kr.ticketing.adminconcert.concert.repository.entity.QConcertEntity.*;
import static co.kr.ticketing.adminconcert.place.repository.entity.QPlaceEntity.*;

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
			.leftJoin(concertEntity.placeEntity, placeEntity)
			.where(
				getListVo.name() != null ? concertEntity.name.contains(getListVo.name()) : null,
				getListVo.placeName() != null ? placeEntity.name.contains(getListVo.name()) : null,
				getListVo.state() != null ? concertEntity.state.eq(getListVo.state()) : null,
				getListVo.openTime() != null ? concertEntity.openTime.loe(getListVo.openTime()) : null
			)
			.limit(100)
			.fetch();
	}
}
