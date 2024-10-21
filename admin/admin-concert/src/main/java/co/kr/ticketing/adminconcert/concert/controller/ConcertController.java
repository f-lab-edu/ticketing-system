package co.kr.ticketing.adminconcert.concert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.kr.ticketing.adminconcert.common.dto.CreatedDto;
import co.kr.ticketing.adminconcert.common.dto.ResponseDto;
import co.kr.ticketing.adminconcert.common.dto.UpdatedDto;
import co.kr.ticketing.adminconcert.concert.controller.request.CreateConcertRequest;
import co.kr.ticketing.adminconcert.concert.controller.request.GetConcertListRequest;
import co.kr.ticketing.adminconcert.concert.controller.request.ModifyConcertSeatsRequest;
import co.kr.ticketing.adminconcert.concert.controller.request.ModifyRoundsRequest;
import co.kr.ticketing.adminconcert.concert.controller.request.SetOpenTimeRequest;
import co.kr.ticketing.adminconcert.concert.controller.request.SetTicketingStartTimeRequest;
import co.kr.ticketing.adminconcert.concert.controller.request.UpdateConcertRequest;
import co.kr.ticketing.adminconcert.concert.controller.request.UpdatePlaceRequest;
import co.kr.ticketing.adminconcert.concert.controller.response.ConcertResponseCode;
import co.kr.ticketing.adminconcert.concert.controller.response.GetConcertListResponse;
import co.kr.ticketing.adminconcert.concert.controller.response.GetConcertResponse;
import co.kr.ticketing.adminconcert.concert.usecase.reader.GetConcertListUseCase;
import co.kr.ticketing.adminconcert.concert.usecase.reader.GetConcertUseCase;
import co.kr.ticketing.adminconcert.concert.usecase.writer.ChangeStateToCloseUseCase;
import co.kr.ticketing.adminconcert.concert.usecase.writer.CreateConcertUseCase;
import co.kr.ticketing.adminconcert.concert.usecase.writer.ModifyConcertSeatsUseCase;
import co.kr.ticketing.adminconcert.concert.usecase.writer.ModifyRoundsUseCase;
import co.kr.ticketing.adminconcert.concert.usecase.writer.SetOpenTimeUseCase;
import co.kr.ticketing.adminconcert.concert.usecase.writer.SetTicketingStartTimeUseCase;
import co.kr.ticketing.adminconcert.concert.usecase.writer.UpdateConcertUseCase;
import co.kr.ticketing.adminconcert.concert.usecase.writer.UpdatePlaceUseCase;
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
	GetConcertListUseCase getConcertListUseCase;
	CreateConcertUseCase createConcertUseCase;
	GetConcertUseCase getConcertUseCase;
	UpdateConcertUseCase updateConcertUseCase;
	SetOpenTimeUseCase setOpenTimeUseCase;
	SetTicketingStartTimeUseCase setTicketingStartTimeUseCase;
	ModifyRoundsUseCase modifyRoundsUseCase;
	UpdatePlaceUseCase updatePlaceUseCase;
	ModifyConcertSeatsUseCase modifyConcertSeatsUseCase;
	ChangeStateToCloseUseCase changeStateToCloseUseCase;

	@GetMapping
	public ResponseEntity<ResponseDto<GetConcertListResponse>> getList(
		@ModelAttribute @Valid GetConcertListRequest request) {
		var response = getConcertListUseCase.execute(request);

		return ResponseEntity.ok(
			new ResponseDto<>(ConcertResponseCode.CREATED_CONCERT.name(), response));
	}

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

	@PatchMapping("/{id}")
	public ResponseEntity<ResponseDto<UpdatedDto>> update(
		@PathVariable @Positive Long id,
		@RequestBody @Valid UpdateConcertRequest request
	) {
		long updatedId = updateConcertUseCase.execute(id, request);

		return ResponseEntity.ok(
			new ResponseDto<>(ConcertResponseCode.UPDATE_CONCERT.name(), UpdatedDto.from(updatedId))
		);
	}

	@PutMapping("/{id}/open-time")
	public ResponseEntity<ResponseDto<UpdatedDto>> setOpenTime(
		@PathVariable @Positive Long id,
		@RequestBody @Valid SetOpenTimeRequest request
	) {
		long updatedId = setOpenTimeUseCase.execute(id, request);

		return ResponseEntity.ok(
			new ResponseDto<>(ConcertResponseCode.SET_CONCERT_OPEN_TIME.name(), UpdatedDto.from(updatedId))
		);
	}

	@PutMapping("/{id}/ticketing-start-time")
	public ResponseEntity<ResponseDto<UpdatedDto>> setTicketingStartTime(
		@PathVariable @Positive Long id,
		@RequestBody @Valid SetTicketingStartTimeRequest request
	) {
		long updatedId = setTicketingStartTimeUseCase.execute(id, request);

		return ResponseEntity.ok(
			new ResponseDto<>(ConcertResponseCode.SET_CONCERT_TICKETING_START_TIME.name(), UpdatedDto.from(updatedId))
		);
	}

	@PutMapping("/{id}/rounds")
	public ResponseEntity<ResponseDto<UpdatedDto>> modifyRounds(
		@PathVariable @Positive Long id,
		@RequestBody @Valid ModifyRoundsRequest request
	) {
		long updatedId = modifyRoundsUseCase.execute(id, request);

		return ResponseEntity.ok(
			new ResponseDto<>(ConcertResponseCode.MODIFY_ROUNDS.name(), UpdatedDto.from(updatedId))
		);
	}

	@PutMapping("/{id}/place")
	public ResponseEntity<ResponseDto<UpdatedDto>> updatePlace(
		@PathVariable @Positive Long id,
		@RequestBody @Valid UpdatePlaceRequest request
	) {
		long updatedId = updatePlaceUseCase.execute(id, request);

		return ResponseEntity.ok(
			new ResponseDto<>(ConcertResponseCode.UPDATE_CONCERT_PLACE.name(), UpdatedDto.from(updatedId))
		);
	}

	@PatchMapping("/{id}/seats")
	public ResponseEntity<ResponseDto<UpdatedDto>> modifyConcertSeats(
		@PathVariable @Positive Long id,
		@RequestBody @Valid ModifyConcertSeatsRequest request
	) {
		long updatedId = modifyConcertSeatsUseCase.execute(id, request);

		return ResponseEntity.ok(
			new ResponseDto<>(ConcertResponseCode.UPDATE_CONCERT_SEATS.name(), UpdatedDto.from(updatedId))
		);
	}

	@PutMapping("/{id}/close")
	public ResponseEntity<ResponseDto<UpdatedDto>> changeStateToClose(@PathVariable @Positive Long id) {
		long updatedId = changeStateToCloseUseCase.execute(id);

		return ResponseEntity.ok(
			new ResponseDto<>(ConcertResponseCode.CHANGE_STATE_TO_CLOSE.name(), UpdatedDto.from(updatedId))
		);
	}
}
