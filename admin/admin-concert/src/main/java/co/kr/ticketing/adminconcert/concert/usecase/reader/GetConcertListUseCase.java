package co.kr.ticketing.adminconcert.concert.usecase.reader;

import java.util.List;

import org.springframework.stereotype.Service;

import co.kr.ticketing.adminconcert.concert.controller.request.GetConcertListRequest;
import co.kr.ticketing.adminconcert.concert.controller.response.GetConcertListResponse;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.service.ConcertService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetConcertListUseCase {
	ConcertService concertService;

	public GetConcertListResponse execute(GetConcertListRequest request) {
		List<Concert> concerts = concertService.getList(request.toVo());

		return GetConcertListResponse.from(concerts);
	}
}
