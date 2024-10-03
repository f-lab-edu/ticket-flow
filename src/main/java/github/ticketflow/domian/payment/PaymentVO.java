package github.ticketflow.domian.payment;

import github.ticketflow.domian.payment.dto.PaymentRequestDTO;
import github.ticketflow.domian.ticket.TicketEntity;
import github.ticketflow.domian.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVO {

    private UserEntity userEntity;
    private int numberOfTicket;
    private List<TicketEntity> ticketEntities;
    private BigDecimal paymentPrice;

    public PaymentVO(UserEntity userEntity, List<TicketEntity> ticketEntities, PaymentRequestDTO dto) {
        this.userEntity = userEntity;
        this.numberOfTicket = dto.getNumberOfTicket();
        this.ticketEntities = ticketEntities;
        this.paymentPrice = dto.getPaymentPrice();
    }
}
