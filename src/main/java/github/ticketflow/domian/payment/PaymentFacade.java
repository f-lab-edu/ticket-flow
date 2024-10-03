package github.ticketflow.domian.payment;

import github.ticketflow.domian.payment.dto.PaymentRequestDTO;
import github.ticketflow.domian.payment.dto.PaymentResponseDTO;
import github.ticketflow.domian.paymentTicket.PaymentTicketEntity;
import github.ticketflow.domian.paymentTicket.PaymentTicketService;
import github.ticketflow.domian.paymentTicket.PaymentTicketVO;
import github.ticketflow.domian.ticket.TicketEntity;
import github.ticketflow.domian.ticket.TicketService;
import github.ticketflow.domian.user.UserService;
import github.ticketflow.domian.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentService paymentService;
    private final PaymentTicketService paymentTicketService;
    private final TicketService ticketService;
    private final UserService userService;


    public PaymentEntity getPaymentById(Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    public List<PaymentResponseDTO> getPaymentByUserEntity(Long userId) {
        UserEntity userEntity = userService.getUserById(userId);
        return paymentService.getPaymentByUserEntity(userEntity);
    }

    @Transactional
    public PaymentEntity createPayment(PaymentRequestDTO dto) {
        List<TicketEntity> ticketEntities = new ArrayList<>();

        UserEntity userEntity = userService.getUserById(dto.getUserId());
        dto.getTicketIds().forEach(ticketId -> {
            ticketEntities.add(ticketService.getTicketById(ticketId));
        });

        PaymentVO paymentVO = new PaymentVO(userEntity, ticketEntities, dto);
        PaymentEntity paymentEntity = paymentService.createPayment(paymentVO);

        ticketEntities.forEach(ticketEntity -> {
            PaymentTicketVO paymentTicketVO = new PaymentTicketVO(paymentEntity, ticketEntity);
            paymentTicketService.createPaymentTicket(paymentTicketVO);
        });

        return paymentEntity;
    }


    public PaymentEntity deletedPayment(Long paymentId) {
        PaymentEntity paymentEntity = getPaymentById(paymentId);
        List<PaymentTicketEntity> paymentTickets = paymentEntity.getPaymentTickets();
        paymentTickets.forEach((paymentTicket) -> {
            paymentTicketService.deletedPaymentTicket(paymentId);
        });
        paymentService.deletedPayment(paymentId);
        return paymentEntity;
    }
}
