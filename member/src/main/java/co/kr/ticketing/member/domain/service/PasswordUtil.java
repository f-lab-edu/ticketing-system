package co.kr.ticketing.member.domain.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import co.kr.ticketing.member.domain.model.Member;
import co.kr.ticketing.member.domain.service.dto.PasswordInfo;
import co.kr.ticketing.member.exception.ServerException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PasswordUtil {
	private final int SALT_LENGTH = 16;
	private final String ENCRYPT_ALGORITHM = "SHA-256";

	public PasswordInfo encodePassword(String password) {
		String salt = generateSalt();

		return PasswordInfo.builder()
			.encodedPassword(getEncrypt(password, salt))
			.salt(salt)
			.build();
	}

	public boolean isMatchPassword(Member member, String password) {
		return getEncrypt(password, member.getSalt()).equals(member.getPassword());
	}

	private String generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_LENGTH];
		random.nextBytes(salt);
		StringBuffer sb = new StringBuffer();
		for (byte b : salt) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}

	private String getEncrypt(String password, String salt) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance(ENCRYPT_ALGORITHM);
			md.update((password + salt).getBytes());
			byte[] pwdsalt = md.digest();

			StringBuffer sb = new StringBuffer();
			for (byte b : pwdsalt) {
				sb.append(String.format("%02x", b));
			}

			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new ServerException(e);
		}

		return result;
	}
}
