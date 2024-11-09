package co.kr.ticketing.adminconcert.concert.repository.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.adminconcert.concert.domain.model.Round;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class RoundEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Column(nullable = false)
	private LocalDateTime startDateTime;

	@Column(nullable = false)
	private Integer sequenceNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	private ConcertEntity concertEntity;

	@Builder
	public RoundEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime startDateTime,
		Integer sequenceNumber, ConcertEntity concertEntity) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.startDateTime = startDateTime;
		this.sequenceNumber = sequenceNumber;
		this.concertEntity = concertEntity;
	}

	public static RoundEntity from(ConcertEntity concertEntity, Round round) {
		return RoundEntity.builder()
			.startDateTime(round.startDateTime())
			.concertEntity(concertEntity)
			.sequenceNumber(round.sequenceNumber())
			.build();
	}

	public static List<RoundEntity> from(ConcertEntity concertEntity, List<Round> round) {
		List<RoundEntity> result = new ArrayList<>();

		for (int i = 0; i < round.size(); i++) {
			var curRound = round.get(i);
			result.add(RoundEntity.builder()
				.startDateTime(curRound.startDateTime())
				.concertEntity(concertEntity)
				.sequenceNumber(i + 1)
				.build());
		}

		return result;
	}

	public Round toModel() {
		return Round.builder()
			.id(id)
			.startDateTime(startDateTime)
			.sequenceNumber(sequenceNumber)
			.build();
	}
}
