package github.ticketflow.domian.event.dto;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {

    private Long eventId;
    private EventLocationResponseDTO eventLocationResponseDTO;
    private String eventName;
    private String eventDescription;
    private LocalDate date;
    private LocalTime startTime;
    private LocalDate createAt;
    private LocalDate modifyAt;

    public EventResponseDTO(EventEntity eventEntity) {
        this.eventId = eventEntity.getEventId();
        this.eventLocationResponseDTO = new EventLocationResponseDTO(eventEntity.getEventLocationEntity());
        this.eventName = eventEntity.getEventName();
        this.eventDescription = eventEntity.getEventDescription();
        this.date = eventEntity.getDate();
        this.startTime = eventEntity.getStartTime();
        this.createAt = eventEntity.getCreatedAt();
        this.modifyAt = eventEntity.getModifiedAt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventResponseDTO that = (EventResponseDTO) o;
        return Objects.equals(eventId, that.eventId) && Objects.equals(eventLocationResponseDTO, that.eventLocationResponseDTO) && Objects.equals(eventName, that.eventName) && Objects.equals(eventDescription, that.eventDescription) && Objects.equals(date, that.date) && Objects.equals(startTime, that.startTime) && Objects.equals(createAt, that.createAt) && Objects.equals(modifyAt, that.modifyAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventLocationResponseDTO, eventName, eventDescription, date, startTime, createAt, modifyAt);
    }
}
