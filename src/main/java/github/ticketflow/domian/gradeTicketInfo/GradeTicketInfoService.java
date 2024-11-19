package github.ticketflow.domian.gradeTicketInfo;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.gradeTicket.GradeTicketErrorResponsive;
import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoRequestDTO;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoResponseDTO;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoUpdateRequestDTO;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeTicketInfoService {

    private final GradeTicketInfoRepository gradeTicketInfoRepository;

    public GradeTicketInfoEntity getGradeTicketInfoById(Long gradeTicketInfoId) {
        return gradeTicketInfoRepository.findById(gradeTicketInfoId).orElseThrow(() ->
                new GlobalCommonException(GradeTicketErrorResponsive.NOT_FOUND_GRADE_TICKET));
    }

    public List<GradeTicketInfoResponseDTO> getGradeTicketInfoByEventEntity(EventEntity eventEntity) {
        List<GradeTicketInfoResponseDTO> gradeTicketInfoResponseDTOS = new ArrayList<>();

        List<GradeTicketInfoEntity> gradeTicketEntities = gradeTicketInfoRepository.findAllByEventEntity(eventEntity);

        gradeTicketEntities.forEach(gradeTicketEntity -> {
            gradeTicketInfoResponseDTOS.add(new GradeTicketInfoResponseDTO(gradeTicketEntity));
        });

        return gradeTicketInfoResponseDTOS;
    }

    public GradeTicketInfoEntity getGradeTicketInfoByEventEntityAndSeatGradeEntity(EventEntity eventEntity, SeatGradeEntity seatGradeEntity) {
        return gradeTicketInfoRepository.findByEventEntityAndSeatGradeEntity(eventEntity, seatGradeEntity).orElseThrow(() ->
                new GlobalCommonException(GradeTicketErrorResponsive.NOT_FOUND_GRADE_TICKET));
    }

    public GradeTicketInfoEntity createGradeTicketInfo(GradeTicketInfoRequestDTO dto,
                                                            EventEntity eventEntity,
                                                            EventLocationEntity eventLocationEntity,
                                                            SeatGradeEntity seatGradeEntity) {

        GradeTicketInfoEntity gradeTicketInfoEntity = new GradeTicketInfoEntity(dto, eventEntity, eventLocationEntity, seatGradeEntity);
        return gradeTicketInfoRepository.save(gradeTicketInfoEntity);
    }

    public GradeTicketInfoEntity updateGradeTicketInfo (Long gradeTicketInfoId,
                                                             GradeTicketInfoUpdateRequestDTO dto
                                                             ) {
        GradeTicketInfoEntity gradeTicketEntity = getGradeTicketInfoById(gradeTicketInfoId);

        gradeTicketEntity.update(dto);
        return gradeTicketInfoRepository.save(gradeTicketEntity);
    }

    public GradeTicketInfoEntity updateGradeTicketInfo (Long gradeTicketInfoId,
                                                             GradeTicketInfoUpdateRequestDTO dto,
                                                             GradeTicketInfoUpdate gradeTicketInfoUpdate) {
        GradeTicketInfoEntity gradeTicketEntity = getGradeTicketInfoById(gradeTicketInfoId);

        gradeTicketEntity.update(dto, gradeTicketInfoUpdate);
        return gradeTicketInfoRepository.save(gradeTicketEntity);
    }

    public GradeTicketInfoEntity deleteGradeTicketInfo(Long gradeTicketInfoId) {
        GradeTicketInfoEntity gradeTicketEntity = getGradeTicketInfoById(gradeTicketInfoId);
        gradeTicketInfoRepository.delete(gradeTicketEntity);

        return gradeTicketEntity;
    }
}
