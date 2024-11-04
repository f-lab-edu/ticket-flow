package github.ticketflow.domian.reservation;

import github.ticketflow.domian.reservation.dto.ReserveRequestDTO;
import github.ticketflow.domian.reservation.dto.ReserveResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReserveController {

    private final ReserveService reserveService;

    @PostMapping("/reserve-ticket")
    public ReserveResponseDTO reserveTicket(@RequestBody ReserveRequestDTO reserveRequestDTO) {
        return reserveService.reserveTicket(reserveRequestDTO);
    }

}
