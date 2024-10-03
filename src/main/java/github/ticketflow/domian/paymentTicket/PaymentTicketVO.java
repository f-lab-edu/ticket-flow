package github.ticketflow.domian.paymentTicket;

import github.ticketflow.domian.payment.PaymentEntity;
import github.ticketflow.domian.ticket.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentTicketVO {

    private PaymentEntity paymentEntity;
    private TicketEntity ticketEntity;

}
