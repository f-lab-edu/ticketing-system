package co.kr.ticketing.adminconcert.place.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.kr.ticketing.adminconcert.common.dto.CreatedDto;
import co.kr.ticketing.adminconcert.common.dto.ResponseDto;
import co.kr.ticketing.adminconcert.place.controller.request.AddPlaceRequest;
import co.kr.ticketing.adminconcert.place.controller.response.PlaceResponseCode;
import co.kr.ticketing.adminconcert.place.usecase.AddPlaceUseCase;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/places")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlaceController {
	AddPlaceUseCase addPlaceUseCase;

	@PostMapping
	public ResponseEntity<ResponseDto<CreatedDto>> addPlace(@RequestBody @Valid AddPlaceRequest request) {
		long createdId = addPlaceUseCase.execute(request);

		return ResponseEntity.ok(new ResponseDto<>(PlaceResponseCode.CREATED_PLACE.name(), CreatedDto.from(createdId)));
	}
}
