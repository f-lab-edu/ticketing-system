package co.kr.ticketing.adminconcert.concert.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertState;
import co.kr.ticketing.adminconcert.concert.service.ConcertService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConcertStateChangeSchduler {
	ConcertService concertService;

	@Scheduled(cron = "0 * * * * *", zone = "Asia/Seoul")
	@SchedulerLock(
		name = "schedulingConcertChangeStateToOpen",
		lockAtLeastFor = "5s",
		lockAtMostFor = "15s")
	public void changeStateToOpen() {
		List<Concert> listToOpen = concertService.getListToOpen();
		for (var concert : listToOpen) {
			concertService.changeState(concert, ConcertState.OPEN);
		}
	}
}
