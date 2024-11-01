package co.kr.ticketing.memberconcert.place.repository.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.memberconcert.place.domain.model.Place;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(
	uniqueConstraints = {
		@UniqueConstraint(name = "placeVersionUnique",
			columnNames = {"identifier", "version"}
		)
	})
@DynamicInsert
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PlaceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String identifier;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Long version;

	@Column(nullable = false)
	@ColumnDefault("false")
	private boolean last;

	@OneToMany(mappedBy = "placeEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SeatEntity> seatEntities;

	public Place toModel() {
		return Place.builder()
			.id(id)
			.name(name)
			.address(address)
			.identifier(identifier)
			.version(version)
			.last(last)
			.seats(seatEntities.stream().map(SeatEntity::toModel).toList())
			.build();
	}
}
