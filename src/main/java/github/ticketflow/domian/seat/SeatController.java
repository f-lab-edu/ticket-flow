package github.ticketflow.domian.seat;

import github.ticketflow.domian.seat.dto.SeatRequestDTO;
import github.ticketflow.domian.seat.dto.SeatResponseDTO;
import github.ticketflow.domian.seat.dto.SeatUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/{seatId}")
    public ResponseEntity<SeatResponseDTO> getSeatById(@PathVariable Long seatId) {
        return ResponseEntity.ok(new SeatResponseDTO(seatService.getSeatById(seatId)));
    }

    @GetMapping("/{seatGradeId}")
    public ResponseEntity<List<SeatResponseDTO>> getSeatByGradeId(@PathVariable Long seatGradeId) {
        return ResponseEntity.ok(seatService.getSeatByGradeId(seatGradeId));
    }

    @PostMapping
    public ResponseEntity<SeatResponseDTO> createSeat(@Valid @RequestBody SeatRequestDTO dto) {
        return ResponseEntity.ok(new SeatResponseDTO(seatService.createSeat(dto)));
    }

    @PatchMapping("/{seatId}")
    public ResponseEntity<SeatResponseDTO> updateSeat(@PathVariable Long seatId,
                                                      @Valid @RequestBody SeatUpdateRequestDTO dto) {
        return ResponseEntity.ok(new SeatResponseDTO(seatService.updateSeat(seatId, dto)));
    }

    @DeleteMapping("/{seatId}")
    public ResponseEntity<SeatResponseDTO> deleteSeat(@PathVariable Long seatId) {
        return ResponseEntity.ok(new SeatResponseDTO(seatService.deletedSeat(seatId)));
    }

    @PatchMapping("select/{seatId}")
    public ResponseEntity<SeatResponseDTO> selectSeatById(@PathVariable Long seatId) {
        return ResponseEntity.ok(new SeatResponseDTO(seatService.selectSeat(seatId)));
    }

}
