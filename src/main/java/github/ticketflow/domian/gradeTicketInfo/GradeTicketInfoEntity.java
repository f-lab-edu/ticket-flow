package github.ticketflow.domian.gradeTicketInfo;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoRequestDTO;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoUpdateRequestDTO;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GradeTicketInfo")
@Builder
public class GradeTicketInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_ticket_info_id")
    private Long gradeTicketInfoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventEntity eventEntity;

    @ManyToOne
    @JoinColumn(name = "event_location_id")
    private EventLocationEntity eventLocationEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_grade_id")
    private SeatGradeEntity seatGradeEntity;

    @Column(name = "number_total_ticket")
    private int numberTotalTicket;

    @Column(name = "number_of_remaining_tickets")
    private int numberOfRemainingTickets;

    public GradeTicketInfoEntity(GradeTicketInfoRequestDTO dto,
                                 EventEntity eventEntity,
                                 EventLocationEntity eventLocationEntity,
                                 SeatGradeEntity seatGradeEntity) {
        this.eventEntity = eventEntity;
        this.eventLocationEntity = eventLocationEntity;
        this.seatGradeEntity = seatGradeEntity;
        this.numberTotalTicket = dto.getNumberTotalTicket();
        this.numberOfRemainingTickets = dto.getNumberOfRemainingTickets();
    }

    public GradeTicketInfoEntity update(GradeTicketInfoUpdateRequestDTO dto) {
        if (dto.getNumberTotalTicket() != this.numberTotalTicket) {
            this.numberTotalTicket = dto.getNumberTotalTicket();
        }

        if (dto.getNumberOfRemainingTickets() != this.numberOfRemainingTickets) {
            this.numberOfRemainingTickets = dto.getNumberOfRemainingTickets();
        }

        return this;
    }

    public GradeTicketInfoEntity update(GradeTicketInfoUpdateRequestDTO dto, GradeTicketInfoUpdate gradeTicketInfoUpdate) {
        if (gradeTicketInfoUpdate.getEventEntity() != null) {
            this.eventEntity = gradeTicketInfoUpdate.getEventEntity();
            this.eventLocationEntity = gradeTicketInfoUpdate.getEventEntity().getEventLocationEntity();
        }

        if (gradeTicketInfoUpdate.getEventLocationEntity() != null) {
            this.eventLocationEntity = gradeTicketInfoUpdate.getEventLocationEntity();
        }

        if (gradeTicketInfoUpdate.getSeatGradeEntity() != null) {
            this.seatGradeEntity = gradeTicketInfoUpdate.getSeatGradeEntity();
        }

        if (dto.getNumberTotalTicket() != this.numberTotalTicket) {
            this.numberTotalTicket = dto.getNumberTotalTicket();
        }

        if (dto.getNumberOfRemainingTickets() != this.numberOfRemainingTickets) {
            this.numberOfRemainingTickets = dto.getNumberOfRemainingTickets();
        }

        return this;
    }
}
