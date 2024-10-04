package github.ticketflow.domian.payment.dto;

import github.ticketflow.domian.payment.PaymentEntity;
import github.ticketflow.domian.paymentTicket.PaymentTicketEntity;
import github.ticketflow.domian.ticket.dto.TicketResponseDTO;
import github.ticketflow.domian.user.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDTO {

    private Long paymentId;
    private UserResponseDTO userResponseDTO;
    private int numberOfTicket;
    private BigDecimal paymentPrice;
    private List<TicketResponseDTO> tickets;
    private LocalDate createdAt;

    public PaymentResponseDTO(PaymentEntity paymentEntity) {
        this.paymentId = paymentEntity.getPaymentId();
        this.userResponseDTO = new UserResponseDTO(paymentEntity.getUserEntity());
        this.numberOfTicket = paymentEntity.getNumberOfTicket();
        this.paymentPrice = paymentEntity.getPaymentPrice();
        this.tickets = paymentEntity.getPaymentTickets() != null ? paymentEntity.getPaymentTickets()
                .stream()
                .map(PaymentTicketEntity::getTicketEntity)
                .map(TicketResponseDTO::new)
                .collect(Collectors.toList()) : new ArrayList<>();
        this.createdAt = paymentEntity.getCreatedAt();
    }
}
