package github.ticketflow.domian.paymentTicket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTicketRepository extends JpaRepository<PaymentTicketEntity, Long> {
}
