package co.kr.ticketing.adminconcert.concert.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import co.kr.ticketing.adminconcert.common.exception.BadRequestException;
import co.kr.ticketing.adminconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.adminconcert.concert.controller.response.ConcertErrorResponseCode;
import co.kr.ticketing.adminconcert.concert.domain.infrastructure.ConcertRepository;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertSeat;
import co.kr.ticketing.adminconcert.concert.domain.model.Round;
import co.kr.ticketing.adminconcert.concert.service.dto.CreateConcertDto;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import co.kr.ticketing.adminconcert.place.service.PlaceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertService {
	ConcertRepository concertRepository;
	PlaceService placeService;

	public Concert get(Long id) {
		return concertRepository.find(id)
			.orElseThrow(() -> new ResourceNotFoundException("concert", id));
	}

	public long create(CreateConcertDto createDto) {
		Place place = placeService.get(createDto.placeId());
		return concertRepository.create(createDto.toModel(place.seats()));
	}

	public long update(Concert concert) {
		if (!concert.isPossibleUpdate()) {
			throw new BadRequestException(ConcertErrorResponseCode.NOT_POSSIBLE_UPDATE_STATE.name(), "수정 가능한 상태가 아닙니다");
		}

		return concertRepository.update(concert);
	}

	public long setOpenTime(Concert concert, LocalDateTime openTime) {
		Concert newConcert = concert.setOpenTime(openTime);

		return concertRepository.setOpenTime(newConcert);
	}

	public long setTicketingStartTime(Concert concert, LocalDateTime ticketingStartTime) {
		Concert newConcert = concert.setTicketingStartTime(ticketingStartTime);

		return concertRepository.setTicketingStartTime(newConcert);
	}

	public long modifyRounds(Concert concert, List<Round> rounds) {
		Concert newConcert = concert.setRounds(rounds);

		return concertRepository.modifyRounds(newConcert);
	}

	public long updatePlace(Concert concert, Place place, List<ConcertSeat> seatModel) {
		Concert newConcert = concert.updatePlace(place, seatModel);

		return concertRepository.updatePlace(newConcert);
	}
}
