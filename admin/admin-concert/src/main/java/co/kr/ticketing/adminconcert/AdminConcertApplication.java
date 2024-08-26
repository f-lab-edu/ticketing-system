package co.kr.ticketing.adminconcert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AdminConcertApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminConcertApplication.class, args);
	}

}
