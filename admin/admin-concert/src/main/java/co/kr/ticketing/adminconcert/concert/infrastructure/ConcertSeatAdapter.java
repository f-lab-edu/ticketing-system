package co.kr.ticketing.adminconcert.concert.infrastructure;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.adminconcert.common.exception.BadRequestException;
import co.kr.ticketing.adminconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.adminconcert.concert.domain.infrastructure.ConcertSeatRepository;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertSeat;
import co.kr.ticketing.adminconcert.concert.repository.ConcertJpaRepository;
import co.kr.ticketing.adminconcert.concert.repository.ConcertSeatJpaRepository;
import co.kr.ticketing.adminconcert.concert.repository.RoundJpaRepository;
import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertEntity;
import co.kr.ticketing.adminconcert.concert.repository.entity.ConcertSeatEntity;
import co.kr.ticketing.adminconcert.concert.service.dto.ModifyConcertSeatVo;
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
	RoundJpaRepository roundJpaRepository;

	@Override
	public void create(long concertId, List<ConcertSeat> concertSeats) {
		ConcertEntity concertEntity = concertJpaRepository.findById(concertId)
			.orElseThrow(() -> new ResourceNotFoundException("concert", concertId));

		int totalRoundCount = roundJpaRepository.countByConcertEntityId(concertId);

		concertSeatJpaRepository.saveAll(
			ConcertSeatEntity.from(concertEntity, concertSeats, totalRoundCount)
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

	@Override
	@Transactional
	public void modify(Concert concert, List<ModifyConcertSeatVo> modifyVo) {
		List<ConcertSeatEntity> registeredConcertSeatEntities = concertSeatJpaRepository.getByConcertEntityId(
			concert.id());

		for (var vo : modifyVo) {
			ConcertSeatEntity toModifyConcertEntity = registeredConcertSeatEntities.stream()
				.filter(concertSeatEntity -> concertSeatEntity.getId().equals(vo.id()))
				.findAny()
				.orElseThrow(() -> new BadRequestException("해당 좌석은 등록되지 않은 좌석이라 수정할 수 없습니다"));

			toModifyConcertEntity.modify(vo);
		}
	}
}
