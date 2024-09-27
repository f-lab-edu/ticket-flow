package github.ticketflow.domian.seatGrade;

import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.seatGrade.dto.SeatGradeRequestDTO;
import github.ticketflow.domian.seatGrade.dto.SeatGradeResponseDTO;
import github.ticketflow.domian.seatGrade.dto.SeatGradeUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SeatGrade")
public class SeatGradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_grade")
    private Long seatGradeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_location_id")
    private EventLocationEntity eventLocation;

    @Column(name = "seat_grade_name")
    private String seatGradeName;

    @Column(name = "seat_grade_price")
    private BigDecimal seatGradePrice;

    @Column(name = "seat_grade_total_seats")
    private int seatGradeTotalSeats;

    public SeatGradeEntity(SeatGradeRequestDTO dto,
                           EventLocationEntity eventLocation) {
        this.eventLocation = eventLocation;
        this.seatGradeName = dto.getSeatGradeName();
        this.seatGradePrice = dto.getSeatGradePrice();
        this.seatGradeTotalSeats = dto.getSeatGradeTotalSeats();
    }

    public SeatGradeEntity(SeatGradeResponseDTO responseDTO) {
        this.seatGradeId = responseDTO.getSeatGradeId();
        this.eventLocation = new EventLocationEntity(responseDTO.getEventLocation());
        this.seatGradeName = responseDTO.getSeatGradeName();
        this.seatGradePrice = responseDTO.getSeatGradePrice();
        this.seatGradeTotalSeats = responseDTO.getSeatGradeTotalSeats();
    }

    public SeatGradeEntity update(SeatGradeUpdateRequestDTO dto,
                                  EventLocationEntity eventLocation) {
        if (eventLocation != null) {
            this.eventLocation = eventLocation;
        }
        if (dto.getSeatGradeName() != null) {
            this.seatGradeName = dto.getSeatGradeName();
        }
        if (dto.getSeatGradePrice() != null &&
                !dto.getSeatGradePrice().equals(this.seatGradePrice)) {
            this.seatGradePrice = dto.getSeatGradePrice();
        }
        if (dto.getSeatGradeTotalSeats() != this.seatGradeTotalSeats) {
            this.seatGradeTotalSeats = dto.getSeatGradeTotalSeats();
        }
        return this;
    }
}
