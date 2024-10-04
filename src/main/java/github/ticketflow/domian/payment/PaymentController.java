package github.ticketflow.domian.payment;

import github.ticketflow.domian.payment.dto.PaymentRequestDTO;
import github.ticketflow.domian.payment.dto.PaymentResponseDTO;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentFacade paymentFacade;

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable Long paymentId) {
        return ResponseEntity.ok(new PaymentResponseDTO(paymentFacade.getPaymentById(paymentId)));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentByUserEntity(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentFacade.getPaymentByUserEntity(userId));
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody PaymentRequestDTO dto) {
        return ResponseEntity.ok(new PaymentResponseDTO(paymentFacade.createPayment(dto)));
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDTO> deletePayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(new PaymentResponseDTO(paymentFacade.deletedPayment(paymentId)));
    }

}
