package co.kr.ticketing.adminconcert.place.service;

import java.util.UUID;

import org.springframework.stereotype.Component;

import co.kr.ticketing.adminconcert.place.domain.IdentifierGenerator;

@Component
public class UUIDGenerator implements IdentifierGenerator {
	@Override
	public String generate() {
		return UUID.randomUUID().toString();
	}
}
