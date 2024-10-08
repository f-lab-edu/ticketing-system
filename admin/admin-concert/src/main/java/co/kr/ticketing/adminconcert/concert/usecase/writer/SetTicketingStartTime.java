package co.kr.ticketing.adminconcert.concert.usecase.writer;

import org.springframework.stereotype.Service;

import co.kr.ticketing.adminconcert.concert.controller.request.SetTicketingStartTimeRequest;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.service.ConcertService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SetTicketingStartTime {
	ConcertService concertService;

	public long execute(Long id, SetTicketingStartTimeRequest request) {
		Concert concert = concertService.get(id);

		return concertService.setTicketingStartTime(concert, request.ticketingStartTime());
	}
}
