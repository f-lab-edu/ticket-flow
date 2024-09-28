package github.ticketflow.domian.gradeTicketInfo;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.event.EventService;
import github.ticketflow.domian.event.dto.EventResponseDTO;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoRequestDTO;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoResponseDTO;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoUpdateRequestDTO;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import github.ticketflow.domian.seatGrade.SeatGradeService;
import github.ticketflow.domian.seatGrade.dto.SeatGradeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GradeTicketInfoFacade {

    private final GradeTicketInfoService gradeTicketInfoService;
    private final EventService eventService;
    private final SeatGradeService seatGradeService;

    public GradeTicketInfoResponseDTO getGradeTicketInfoById(Long gradeTicketInfoId) {
        return gradeTicketInfoService.getGradeTicketInfoById(gradeTicketInfoId);
    }

    public List<GradeTicketInfoResponseDTO> getGradeTicketInfoByEventEntity(Long eventId) {
        EventEntity eventEntity = getEventEntity(eventId);

        return gradeTicketInfoService.getGradeTicketInfoByEventEntity(eventEntity);
    }

    public GradeTicketInfoResponseDTO getGradeTicketInfoByEventEntityAndSeatGradeEntity(Long eventId, Long seatGradeId) {
        EventEntity eventEntity = getEventEntity(eventId);
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(seatGradeId);

        return gradeTicketInfoService.getGradeTicketInfoByEventEntityAndSeatGradeEntity(eventEntity, seatGradeEntity);
    }

    @Transactional
    public GradeTicketInfoResponseDTO createGradeTicketInfo(GradeTicketInfoRequestDTO dto) {
        EventEntity eventEntity = getEventEntity(dto.getEventId());
        EventLocationEntity eventLocationEntity = eventEntity.getEventLocation();
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(dto.getSeatGradeId());

        return gradeTicketInfoService.createGradeTicketInfo(dto, eventEntity, eventLocationEntity, seatGradeEntity);
    }

    @Transactional
    public GradeTicketInfoResponseDTO updateGradeTicketInfo(Long gradeTicketInfoId, GradeTicketInfoUpdateRequestDTO dto) {
        if (dto.getEventId() != null || dto.getEventLocationId() != null ||dto.getSeatGradeId() != null) {
            EventEntity eventEntity = getEventEntity(dto.getEventId());
            EventLocationEntity eventLocationEntity = eventEntity.getEventLocation();
            SeatGradeEntity seatGradeEntity = getSeatGradeEntity(dto.getSeatGradeId());
            GradeTicketInfoUpdate gradeTicketInfoUpdate = new GradeTicketInfoUpdate(eventEntity, eventLocationEntity, seatGradeEntity);

            return gradeTicketInfoService.updateGradeTicketInfo(gradeTicketInfoId, dto, gradeTicketInfoUpdate);
        }
        return gradeTicketInfoService.updateGradeTicketInfo(gradeTicketInfoId, dto);
    }

    @Transactional
    public GradeTicketInfoResponseDTO deleteGradeTicketInfo(Long gradeTicketInfoId) {
       return gradeTicketInfoService.deleteGradeTicketInfo(gradeTicketInfoId);
    }

    private EventEntity getEventEntity(Long eventId) {
        EventResponseDTO eventResponseDTO = eventService.getEventById(eventId);
        EventEntity eventEntity = new EventEntity(eventResponseDTO);
        return eventEntity;
    }

    private SeatGradeEntity getSeatGradeEntity(Long seatGradeId) {
        SeatGradeResponseDTO seatGradeResponseDTO = seatGradeService.getSeatGradeById(seatGradeId);
        SeatGradeEntity seatGradeEntity = new SeatGradeEntity(seatGradeResponseDTO);
        return seatGradeEntity;
    }
}
