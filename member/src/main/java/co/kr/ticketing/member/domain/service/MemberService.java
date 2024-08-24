package co.kr.ticketing.member.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.kr.ticketing.member.domain.model.Member;
import co.kr.ticketing.member.domain.repository.MemberRepository;
import co.kr.ticketing.member.domain.service.dto.CreateMemberDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberService {
	MemberRepository memberRepository;

	public Optional<Member> findByPhoneNumber(String phoneNumber) {
		return memberRepository.findByPhoneNumber(phoneNumber);
	}

	public boolean isExistMember(String phoneNumber) {
		return memberRepository.findByPhoneNumber(phoneNumber).isPresent();
	}

	public boolean isMatchPassword(Member member, String password) {
		return PasswordUtil.isMatchPassword(member, password);
	}

	public Member create(CreateMemberDto createDto) {
		return memberRepository.save(createDto.toModel(PasswordUtil.encodePassword(createDto.password())));
	}
}
