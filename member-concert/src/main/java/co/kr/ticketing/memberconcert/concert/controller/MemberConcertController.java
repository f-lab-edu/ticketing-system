package co.kr.ticketing.memberconcert.concert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.kr.ticketing.memberconcert.common.dto.PageResponse;
import co.kr.ticketing.memberconcert.common.dto.PageableRequest;
import co.kr.ticketing.memberconcert.common.dto.ResponseDto;
import co.kr.ticketing.memberconcert.concert.controller.response.GetConcertListResponse;
import co.kr.ticketing.memberconcert.concert.controller.response.GetConcertResponse;
import co.kr.ticketing.memberconcert.concert.controller.response.GetRoundStatusResponse;
import co.kr.ticketing.memberconcert.concert.controller.response.MemberConcertResponseCode;
import co.kr.ticketing.memberconcert.concert.usecase.reader.GetConcertListUseCase;
import co.kr.ticketing.memberconcert.concert.usecase.reader.GetConcertUseCase;
import co.kr.ticketing.memberconcert.concert.usecase.reader.GetRoundStatusUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
	GetConcertUseCase getConcertUseCase;
	GetRoundStatusUseCase getRoundStatusUseCase;

	@GetMapping
	public ResponseEntity<ResponseDto<PageResponse<GetConcertListResponse>>> getConcertList(
		@ModelAttribute @Valid PageableRequest pageable) {
		var response = getConcertListUseCase.execute(pageable);

		return ResponseEntity.ok(
			new ResponseDto<>(MemberConcertResponseCode.GET_CONCERT_LIST.name(), response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto<GetConcertResponse>> getByConcertId(@PathVariable @Positive Long id) {
		var response = getConcertUseCase.execute(id);

		return ResponseEntity.ok(
			new ResponseDto<>(MemberConcertResponseCode.GET_CONCERT.name(), response));
	}

	@GetMapping("/{id}/rounds/{roundId}")
	public ResponseEntity<ResponseDto<GetRoundStatusResponse>> getRoundStatus(
		@PathVariable @Positive Long id,
		@PathVariable @Positive Integer roundId) {
		var response = getRoundStatusUseCase.execute(id, roundId);

		return ResponseEntity.ok(
			new ResponseDto<>(MemberConcertResponseCode.GET_CONCERT_ROUND_STATUS.name(), response));
	}
}
