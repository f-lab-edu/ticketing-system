package co.kr.ticketing.adminconcert.place.controller.request;

import jakarta.validation.constraints.NotBlank;

public record GetPlaceRequest(
	@NotBlank
	String name
) {
}
