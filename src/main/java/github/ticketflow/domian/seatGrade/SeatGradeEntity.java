package github.ticketflow.domian.seatGrade;

import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.seatGrade.dto.SeatGradeRequestDTO;
import github.ticketflow.domian.seatGrade.dto.SeatGradeResponseDTO;
import github.ticketflow.domian.seatGrade.dto.SeatGradeUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SeatGrade")
@Builder
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatGradeEntity that = (SeatGradeEntity) o;
        return seatGradeTotalSeats == that.seatGradeTotalSeats && Objects.equals(seatGradeId, that.seatGradeId) && Objects.equals(eventLocation, that.eventLocation) && Objects.equals(seatGradeName, that.seatGradeName) && Objects.equals(seatGradePrice, that.seatGradePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatGradeId, eventLocation, seatGradeName, seatGradePrice, seatGradeTotalSeats);
    }
}
