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
import co.kr.ticketing.memberreservation.reservationtoken.controller.request.CreateReservationTokenRequest;
import co.kr.ticketing.memberreservation.reservationtoken.controller.response.CreateReservationTokenResponse;
import co.kr.ticketing.memberreservation.reservationtoken.controller.response.GetCurrentReservationTokenPositionResponse;
import co.kr.ticketing.memberreservation.reservationtoken.controller.response.MemberReservationTokenResponseCode;
import co.kr.ticketing.memberreservation.reservationtoken.usecase.writer.CreateReservationTokenUseCase;
import co.kr.ticketing.memberreservation.reservationtoken.usecase.writer.GetCurrentReservationTokenPositionUseCase;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Validated
@RestController
@RequestMapping(value = "/reservations/tokens")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationTokenController {
	CreateReservationTokenUseCase createReservationTokenUseCase;
	GetCurrentReservationTokenPositionUseCase getCurrentReservationTokenPositionUseCase;

	@PostMapping
	public ResponseEntity<ResponseDto<CreateReservationTokenResponse>> createReservationToken(
		@RequestBody @Valid CreateReservationTokenRequest request) {
		var response = createReservationTokenUseCase.execute(request);

		return ResponseEntity.ok(
			new ResponseDto<>(MemberReservationTokenResponseCode.CREATE_RESERVATION_TOKEN.name(), response));
	}

	@GetMapping("/position")
	@ReservationTokenCheck
	public ResponseEntity<ResponseDto<GetCurrentReservationTokenPositionResponse>> getCurrentReservationTokenPosition(
		@RequestParam String token) {
		var response = getCurrentReservationTokenPositionUseCase.execute(token);

		return ResponseEntity.ok(
			new ResponseDto<>(MemberReservationTokenResponseCode.GET_RESERVATION_TOKEN_POSITION.name(), response));
	}
}
