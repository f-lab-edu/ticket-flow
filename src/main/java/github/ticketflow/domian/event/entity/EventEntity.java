package github.ticketflow.domian.event.entity;

import github.ticketflow.domian.event.dto.EventRequestDTO;
import github.ticketflow.domian.event.dto.EventUpdateRequestDTO;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Event")
@Builder
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_location_id")
    private EventLocationEntity eventLocation;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @LastModifiedDate
    @Column(name = "modify_at")
    private LocalDate modifiedAt;

    public EventEntity(EventRequestDTO dto, EventLocationEntity eventLocationEntity) {
        this.eventLocation = eventLocationEntity;
        this.eventName = dto.getEventName();
        this.eventDescription = dto.getEventDescription();
        this.date = dto.getDate();
        this.startTime = dto.getStartTime();
    }

    public EventEntity update(EventUpdateRequestDTO dto, EventLocationEntity eventLocationEntity) {
        if (eventLocationEntity != null) {
            this.eventLocation = eventLocationEntity;
        }
        if (dto.getEventName() != null) {
            this.eventName = dto.getEventName();
        }
        if (dto.getEventDescription() != null) {
            this.eventDescription = dto.getEventDescription();
        }
        if (dto.getDate() != null) {
            this.date = dto.getDate();
        }
        if (dto.getStartTime() != null) {
            this.startTime = dto.getStartTime();
        }
        return this;
    }
}