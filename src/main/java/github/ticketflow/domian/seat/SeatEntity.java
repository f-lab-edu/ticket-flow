package github.ticketflow.domian.seat;

import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.seat.dto.SeatRequestDTO;
import github.ticketflow.domian.seat.dto.SeatUpdateRequestDTO;
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
@Table(name = "Seat")
@Builder
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_grade_id")
    private SeatGradeEntity seatGradeEntity;

    @Column(name = "seat_zone")
    private String seatZone;

    @Column(name = "seat_row")
    private int seatRow;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "seat_status")
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    public SeatEntity(SeatRequestDTO dto, SeatGradeEntity seatGradeEntity) {
        this.seatGradeEntity = seatGradeEntity;
        this.seatZone = dto.getSeatZone();
        this.seatRow = dto.getSeatRow();
        this.seatNumber = dto.getSeatNumber();
        this.seatStatus = SeatStatus.EMPTY;
    }

    public SeatEntity(Long id, SeatGradeEntity seatGradeEntity, String seatZone, int seatRow, int seatNumber) {
        this.seatId = id;
        this.seatGradeEntity = seatGradeEntity;
        this.seatZone = seatZone;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.seatStatus = SeatStatus.EMPTY;
    }

    public SeatEntity updateSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
        return this;
    }

    public SeatEntity update(SeatUpdateRequestDTO dto, SeatGradeEntity seatGradeEntity) {
        if(dto.getSeatZone() != null) {
            this.seatZone = dto.getSeatZone();
        }
        if (dto.getSeatRow() != this.seatRow) {
            this.seatRow = dto.getSeatRow();
        }
        if (dto.getSeatNumber() != this.seatNumber) {
            this.seatNumber = dto.getSeatNumber();
        }
        if (seatGradeEntity != null) {
            this.seatGradeEntity = seatGradeEntity;
        }
        return this;
    }
}
