package co.kr.ticketing.adminconcert.place.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.adminconcert.place.domain.IdentifierGenerator;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(uniqueConstraints = {
	@UniqueConstraint(name = "placeVersionUnique",
		columnNames = {"identifier", "version"}
	)
})
@DynamicInsert
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Place {
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

	@OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Seat> seats = new ArrayList<>();

	@Builder
	public Place(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, String address,
		String identifier, Long version, boolean last, List<Seat> seats) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.name = name;
		this.address = address;
		this.identifier = identifier;
		this.version = version;
		this.last = last;
		setSeats(seats);
	}

	private void setSeats(List<Seat> seats) {
		this.seats.clear();
		this.seats.addAll(seats);
		seats.forEach(seat -> {
			if (seat.getPlace() != this) {
				seat.setPlace(this);
			}
		});
	}

	public Seat getSeat(Long seatId) {
		return seats.stream().filter(placeSeat -> placeSeat.getId().equals(seatId)).findAny()
			.orElse(null);
	}

	public static Place getNewPlace(Place place, IdentifierGenerator generator) {
		return Place.builder()
			.name(place.getName())
			.address(place.getAddress())
			.identifier(generator.generate())
			.version(0L)
			.last(true)
			.seats(List.copyOf(place.getSeats()))
			.build();
	}

	public Place getNextPlace(Place beforePlace) {
		return Place.builder()
			.name(name)
			.address(address)
			.identifier(identifier)
			.version(beforePlace.getNextVersion())
			.last(true)
			.seats(List.copyOf(getSeats()))
			.build();
	}

	public Long getNextVersion() {
		return this.version + 1;
	}

	public void setNotLast() {
		this.last = false;
	}
}
