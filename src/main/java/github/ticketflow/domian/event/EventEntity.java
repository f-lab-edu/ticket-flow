package github.ticketflow.domian.event;

import github.ticketflow.domian.event.dto.EventRequestDTO;
import github.ticketflow.domian.event.dto.EventResponseDTO;
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
import java.time.LocalTime;
import java.util.Objects;

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
    private EventLocationEntity eventLocationEntity;

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
        this.eventLocationEntity = eventLocationEntity;
        this.eventName = dto.getEventName();
        this.eventDescription = dto.getEventDescription();
        this.date = dto.getDate();
        this.startTime = dto.getStartTime();
    }

    public EventEntity(EventResponseDTO dto) {
        this.eventId = dto.getEventId();
        this.eventLocationEntity = new EventLocationEntity(dto.getEventLocationResponseDTO());
        this.eventName = dto.getEventName();
        this.eventDescription = dto.getEventDescription();
        this.date = dto.getDate();
        this.startTime = dto.getStartTime();
        this.createdAt = dto.getCreateAt();
        this.modifiedAt = dto.getModifyAt();
    }

    public EventEntity update(EventUpdateRequestDTO dto) {
        return getEventEntity(dto);
    }

    public EventEntity update(EventUpdateRequestDTO dto, EventLocationEntity eventLocationEntity) {
        if (eventLocationEntity != null) {
            this.eventLocationEntity = eventLocationEntity;
        }
        return getEventEntity(dto);
    }

    private EventEntity getEventEntity(EventUpdateRequestDTO dto) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEntity that = (EventEntity) o;
        return Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }
}
