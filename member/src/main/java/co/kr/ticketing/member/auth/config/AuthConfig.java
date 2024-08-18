package co.kr.ticketing.member.auth.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthConfig {
	public static String SECRET_KEY = "test_secret_key";
	public static String PHONE_NUMBER = "phoneNumber";
	public static int TOKEN_VALID_TIME = 86400;
	public static String LOGIN_COOKIE_NAME = "token";
}
