package github.ticketflow.domian.paymentTicket;

import github.ticketflow.domian.payment.PaymentEntity;
import github.ticketflow.domian.ticket.TicketEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PaymentTicket")
@Builder
public class PaymentTicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_ticket_id")
    private Long paymentTicketId;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentEntity paymentEntity;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticketEntity;

    public PaymentTicketEntity(PaymentTicketVO paymentTicketVO) {
        this.paymentEntity = paymentTicketVO.getPaymentEntity();
        this.ticketEntity = paymentTicketVO.getTicketEntity();
    }
}
