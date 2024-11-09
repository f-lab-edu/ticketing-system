package co.kr.ticketing.memberconcert.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class ResourceNotFoundException extends CustomException {
	public ResourceNotFoundException(String domain, Long id) {
		super(HttpStatus.NO_CONTENT, "domain: " + domain + ", id: " + id);
	}

	public ResourceNotFoundException(String domain, String id) {
		super(HttpStatus.NO_CONTENT, "domain: " + domain + ", id: " + id);
	}

	public ResourceNotFoundException(String message) {
		super(HttpStatus.NO_CONTENT, message);
	}
}