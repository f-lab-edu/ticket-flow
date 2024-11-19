package github.ticketflow.domian.seatGrade;

import github.ticketflow.domian.seatGrade.dto.SeatGradeRequestDTO;
import github.ticketflow.domian.seatGrade.dto.SeatGradeResponseDTO;
import github.ticketflow.domian.seatGrade.dto.SeatGradeUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seat-grade")
public class SeatGradeController {

    private final SeatGradeService seatGradeService;

    @GetMapping("/{seatGradeId}")
    public ResponseEntity<SeatGradeResponseDTO> getSeatGradeById(@PathVariable Long seatGradeId) {
        return ResponseEntity.ok(new SeatGradeResponseDTO(seatGradeService.getSeatGradeById(seatGradeId)));
    }

    @GetMapping("event-location/{eventLocationId}")
    public ResponseEntity<List<SeatGradeResponseDTO>> getSeatGradeByEventLocation(@PathVariable Long eventLocationId) {
        return ResponseEntity.ok(seatGradeService.getSeatGradeByEventLocationId(eventLocationId));
    }

    @PostMapping
    public ResponseEntity<SeatGradeResponseDTO> createSeatGrade(@Valid @RequestBody SeatGradeRequestDTO dto) {
        return ResponseEntity.ok(new SeatGradeResponseDTO(seatGradeService.createSeatGrade(dto)));
    }

    @PatchMapping("/{seatGradeId}")
    public ResponseEntity<SeatGradeResponseDTO> updateSeatGrade(@PathVariable Long seatGradeId,
                                                                @Valid @RequestBody SeatGradeUpdateRequestDTO dto) {
        return ResponseEntity.ok(new SeatGradeResponseDTO(seatGradeService.updateSeatGrade(seatGradeId, dto)));
    }

    @DeleteMapping("/{seatGradeId}")
    public ResponseEntity<SeatGradeResponseDTO> deleteSeatGrade(@PathVariable Long seatGradeId) {
        return ResponseEntity.ok(new SeatGradeResponseDTO(seatGradeService.deletedSeatGrade(seatGradeId)));
    }
}
