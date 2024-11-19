package github.ticketflow.domian.gradeTicketInfo;

import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoRequestDTO;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoResponseDTO;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grade-ticket-info")
public class GradeTicketInfoController {

    private final GradeTicketInfoFacade gradeTicketInfoFacade;

    @GetMapping("/{gradeTicketInfoId}")
    public ResponseEntity<GradeTicketInfoResponseDTO> getGradeTicketInfoById(@PathVariable Long gradeTicketInfoId) {
        return ResponseEntity.ok(new GradeTicketInfoResponseDTO(gradeTicketInfoFacade.getGradeTicketInfoById(gradeTicketInfoId)));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<List<GradeTicketInfoResponseDTO>> getGradeTicketInfoByEventEntity(@PathVariable Long eventId) {
        return ResponseEntity.ok(gradeTicketInfoFacade.getGradeTicketInfoByEventEntity(eventId));
    }

    @GetMapping("/{eventId}/{seatGradeId}")
    public ResponseEntity<GradeTicketInfoResponseDTO> getGradeTicketInfoByEventEntityAndSeatGradeEntity(@PathVariable Long eventId,
                                                                                                @PathVariable Long seatGradeId) {
        return ResponseEntity.ok(new GradeTicketInfoResponseDTO(gradeTicketInfoFacade.getGradeTicketInfoByEventEntityAndSeatGradeEntity(eventId, seatGradeId)));
    }

    @PostMapping
    public ResponseEntity<GradeTicketInfoResponseDTO> createGradeTicketInfo(@Valid @RequestBody GradeTicketInfoRequestDTO dto) {
        return ResponseEntity.ok(new GradeTicketInfoResponseDTO(gradeTicketInfoFacade.createGradeTicketInfo(dto)));
    }

    @PatchMapping("/{gradeTicketInfoId}")
    public ResponseEntity<GradeTicketInfoResponseDTO> updateGradeTicketInfo(@PathVariable Long gradeTicketInfoId,
                                                                            @Valid @RequestBody GradeTicketInfoUpdateRequestDTO dto) {
        return ResponseEntity.ok(new GradeTicketInfoResponseDTO(gradeTicketInfoFacade.updateGradeTicketInfo(gradeTicketInfoId, dto)));
    }
    @DeleteMapping("/{gradeTicketInfoId}")
    public ResponseEntity<GradeTicketInfoResponseDTO> deleteGradeTicketInfo(@PathVariable Long gradeTicketInfoId) {
        return ResponseEntity.ok(new GradeTicketInfoResponseDTO(gradeTicketInfoFacade.deleteGradeTicketInfo(gradeTicketInfoId)));
    }

}
