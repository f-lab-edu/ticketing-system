package co.kr.ticketing.memberreservation.reservationtoken.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.kr.ticketing.memberreservation.common.dto.ResponseDto;
import co.kr.ticketing.memberreservation.reservationtoken.aop.ReservationTokenCheck;
import co.kr.ticketing.memberreservation.reservationtoken.controller.request.CreateReservationWaitingTokenRequest;
import co.kr.ticketing.memberreservation.reservationtoken.controller.response.CreateReservationWaitingTokenResponse;
import co.kr.ticketing.memberreservation.reservationtoken.controller.response.GetCurrentReservationWaitingTokenPositionResponse;
import co.kr.ticketing.memberreservation.reservationtoken.controller.response.MemberReservationTokenResponseCode;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenState;
import co.kr.ticketing.memberreservation.reservationtoken.usecase.writer.CreateReservationWaitingTokenUseCase;
import co.kr.ticketing.memberreservation.reservationtoken.usecase.writer.GetCurrentReservationWaitingTokenPositionUseCase;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Validated
@RestController
@RequestMapping(value = "/reservations/tokens/waiting")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationWaitingTokenController {
	CreateReservationWaitingTokenUseCase createReservationWaitingTokenUseCase;
	GetCurrentReservationWaitingTokenPositionUseCase getCurrentReservationWaitingTokenPositionUseCase;

	@PostMapping
	public ResponseEntity<ResponseDto<CreateReservationWaitingTokenResponse>> createReservationToken(
		@RequestBody @Valid CreateReservationWaitingTokenRequest request) {
		var response = createReservationWaitingTokenUseCase.execute(request);

		return ResponseEntity.ok(
			new ResponseDto<>(MemberReservationTokenResponseCode.CREATE_RESERVATION_WAITING_TOKEN.name(), response));
	}

	@GetMapping("/position")
	@ReservationTokenCheck(ReservationTokenState.WAITING)
	public ResponseEntity<ResponseDto<GetCurrentReservationWaitingTokenPositionResponse>> getCurrentReservationWaitingTokenPosition(
		@RequestParam String token) {
		var response = getCurrentReservationWaitingTokenPositionUseCase.execute(token);

		return ResponseEntity.ok(
			new ResponseDto<>(MemberReservationTokenResponseCode.GET_RESERVATION_WAITING_TOKEN_POSITION.name(),
				response));
	}
}
