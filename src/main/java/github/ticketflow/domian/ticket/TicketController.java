package github.ticketflow.domian.ticket;

import github.ticketflow.domian.ticket.dto.TicketRequestDTO;
import github.ticketflow.domian.ticket.dto.TicketResponseDTO;
import github.ticketflow.domian.ticket.dto.TicketUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    private final TicketFacade ticketFacade;

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable Long ticketId) {
        return ResponseEntity.ok(new TicketResponseDTO(ticketFacade.getTicketById(ticketId)));
    }

    @GetMapping("/{seatId}")
    public ResponseEntity<TicketResponseDTO> getTicketBySeatEntity(@PathVariable Long seatId) {
        return ResponseEntity.ok(new TicketResponseDTO(ticketFacade.getTicketBySeatEntity(seatId)));
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@Valid @RequestBody TicketRequestDTO dto) {
        return ResponseEntity.ok(new TicketResponseDTO(ticketFacade.createTicket(dto)));
    }

    @PatchMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable Long ticketId,
                                                          @RequestBody TicketUpdateRequestDTO dto) {
        return ResponseEntity.ok(new TicketResponseDTO(ticketFacade.updateTicket(ticketId, dto)));
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> deleteTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(new TicketResponseDTO(ticketFacade.deleteTicket(ticketId)));
    }
}
