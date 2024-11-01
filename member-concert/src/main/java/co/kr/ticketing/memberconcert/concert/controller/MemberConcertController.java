package co.kr.ticketing.memberconcert.concert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.kr.ticketing.memberconcert.common.dto.PageResponse;
import co.kr.ticketing.memberconcert.common.dto.PageableRequest;
import co.kr.ticketing.memberconcert.common.dto.ResponseDto;
import co.kr.ticketing.memberconcert.concert.controller.response.GetConcertListResponse;
import co.kr.ticketing.memberconcert.concert.controller.response.MemberConcertResponseCode;
import co.kr.ticketing.memberconcert.concert.usecase.reader.GetConcertListUseCase;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Validated
@RestController
@RequestMapping(value = "/concerts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberConcertController {
	GetConcertListUseCase getConcertListUseCase;

	@GetMapping
	public ResponseEntity<ResponseDto<PageResponse<GetConcertListResponse>>> getList(
		@ModelAttribute @Valid PageableRequest pageable) {
		var response = getConcertListUseCase.execute(pageable);

		return ResponseEntity.ok(
			new ResponseDto<>(MemberConcertResponseCode.GET_CONCERT_LIST.name(), response));
	}
}
