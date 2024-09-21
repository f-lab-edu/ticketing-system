package co.kr.ticketing.adminconcert.place.domain.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.adminconcert.place.domain.IdentifierGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
	@UniqueConstraint(name = "placeVersionUnique",
		columnNames = {"identifier", "version"}
	)
})
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class PlaceVersion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Column(nullable = false)
	private String identifier;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Long version;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private Place place;

	public static PlaceVersion newVersion(Place place, IdentifierGenerator generator) {
		return PlaceVersion.builder()
			.identifier(generator.generate())
			.place(place)
			.build();
	}

	public PlaceVersion nextVersion(Place place) {
		return PlaceVersion.builder()
			.identifier(identifier)
			.version(getNextVersion())
			.place(place)
			.build();
	}

	public Long getNextVersion() {
		return this.version + 1;
	}
}
