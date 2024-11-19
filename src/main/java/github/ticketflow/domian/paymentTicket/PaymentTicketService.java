package github.ticketflow.domian.paymentTicket;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.payment.PaymentErrorResponsive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentTicketService {

    private final PaymentTicketRepository paymentTicketRepository;

    public PaymentTicketEntity getPaymentTicketById(Long paymentTicketId) {
        return paymentTicketRepository.findById(paymentTicketId).orElseThrow(() ->
                new GlobalCommonException(PaymentErrorResponsive.NOT_FOUND_PAYMENT));
    }

    public PaymentTicketEntity createPaymentTicket(PaymentTicketVO paymentTicketVO) {
        PaymentTicketEntity paymentTicketEntity = new PaymentTicketEntity(paymentTicketVO);
        return paymentTicketRepository.save(paymentTicketEntity);
    }

    public PaymentTicketEntity deletedPaymentTicket(Long paymentTicketId) {
        PaymentTicketEntity paymentTicketEntity = getPaymentTicketById(paymentTicketId);
        paymentTicketRepository.delete(paymentTicketEntity);
        return paymentTicketEntity;
    }
}
