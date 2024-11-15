package co.kr.ticketing.memberconcert.concert.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.kr.ticketing.memberconcert.common.exception.ResourceNotFoundException;
import co.kr.ticketing.memberconcert.concert.domain.infrastructure.ConcertReadRepository;
import co.kr.ticketing.memberconcert.concert.domain.model.Concert;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertService {
	ConcertReadRepository concertReadRepository;

	public Page<Concert> getPage(Pageable pageable) {
		return concertReadRepository.getPage(pageable);
	}

	public Concert getById(long id) {
		return concertReadRepository.find(id)
			.orElseThrow(() -> new ResourceNotFoundException("concert", id));
	}
}
