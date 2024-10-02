package github.ticketflow.domian.ticket;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.seat.SeatEntity;
import github.ticketflow.domian.ticket.dto.TicketRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventEntity eventEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private SeatEntity seatEntity;

    @Column(name = "ticket_price")
    private BigDecimal ticketPrice;

    @Column(name = "ticket_status")
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    public TicketEntity(EventEntity eventEntity, SeatEntity seatEntity, BigDecimal ticketPrice) {
        this.eventEntity = eventEntity;
        this.seatEntity = seatEntity;
        this.ticketPrice = ticketPrice;
        this.ticketStatus = TicketStatus.NO_PAYMENT;
    }

    public TicketEntity update(TicketUpdateClass ticketUpdateClass) {
        if (ticketUpdateClass.getEventEntity() != null) {
            this.eventEntity = ticketUpdateClass.getEventEntity();

        }
        if(ticketUpdateClass.getSeatEntity() != null) {
            this.seatEntity = ticketUpdateClass.getSeatEntity();

        }
        if(ticketUpdateClass.getTicketPrice() != null) {
            this.ticketPrice = ticketUpdateClass.getTicketPrice();

        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity that = (TicketEntity) o;
        return Objects.equals(ticketId, that.ticketId) && Objects.equals(eventEntity, that.eventEntity) && Objects.equals(seatEntity, that.seatEntity) && Objects.equals(ticketPrice, that.ticketPrice) && ticketStatus == that.ticketStatus && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, eventEntity, seatEntity, ticketPrice, ticketStatus, createdAt);
    }
}