package co.kr.ticketing.memberconcert.concert.usecase.reader;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import co.kr.ticketing.memberconcert.common.dto.PageResponse;
import co.kr.ticketing.memberconcert.common.dto.PageableRequest;
import co.kr.ticketing.memberconcert.concert.controller.response.GetConcertListResponse;
import co.kr.ticketing.memberconcert.concert.domain.model.Concert;
import co.kr.ticketing.memberconcert.concert.service.ConcertService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetConcertListUseCase {
	ConcertService concertService;

	public PageResponse<GetConcertListResponse> execute(PageableRequest pageableRequest) {
		Page<Concert> concerts = concertService.getList(pageableRequest.toPageable());

		return PageResponse.from(concerts, concerts.getContent().stream().map(GetConcertListResponse::from).toList());
	}
}