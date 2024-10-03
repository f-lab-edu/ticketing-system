package co.kr.ticketing.adminconcert.concert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.kr.ticketing.adminconcert.common.dto.CreatedDto;
import co.kr.ticketing.adminconcert.common.dto.ResponseDto;
import co.kr.ticketing.adminconcert.concert.controller.request.CreateConcertRequest;
import co.kr.ticketing.adminconcert.concert.controller.response.ConcertResponseCode;
import co.kr.ticketing.adminconcert.concert.controller.response.GetConcertResponse;
import co.kr.ticketing.adminconcert.concert.usecase.CreateConcertUseCase;
import co.kr.ticketing.adminconcert.concert.usecase.GetConcertUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/concerts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertController {
	CreateConcertUseCase createConcertUseCase;
	GetConcertUseCase getConcertUseCase;

	@PostMapping
	public ResponseEntity<ResponseDto<CreatedDto>> createConcert(@RequestBody @Valid CreateConcertRequest request) {
		long createdId = createConcertUseCase.execute(request);

		return ResponseEntity.ok(
			new ResponseDto<>(ConcertResponseCode.CREATED_CONCERT.name(), CreatedDto.from(createdId)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto<GetConcertResponse>> get(@PathVariable @Positive Long id) {
		var response = getConcertUseCase.execute(id);
		return ResponseEntity.ok(new ResponseDto<>(ConcertResponseCode.GET_CONCERT.name(), response));
	}
}
