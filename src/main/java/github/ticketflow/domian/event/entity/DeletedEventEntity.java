package github.ticketflow.domian.event.entity;

import github.ticketflow.domian.eventLocation.EventLocationEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@Table(name = "DeletedEventEntity")
public class DeletedEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deleted_event_id")
    private Long deletedEventId;

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

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @Column(name = "modify_at")
    private LocalDate modifiedAt;

    @CreatedDate
    @Column(name = "deleted_at", updatable = false)
    private LocalDate deletedAt;

    public DeletedEventEntity(EventEntity eventEntity) {
        this.eventId = eventEntity.getEventId();
        this.eventLocation = eventEntity.getEventLocation();
        this.eventName = eventEntity.getEventName();
        this.eventDescription = eventEntity.getEventDescription();
        this.date = eventEntity.getDate();
        this.startTime = eventEntity.getStartTime();
        this.createdAt = eventEntity.getCreatedAt();
        this.modifiedAt = eventEntity.getModifiedAt();
    }
}
