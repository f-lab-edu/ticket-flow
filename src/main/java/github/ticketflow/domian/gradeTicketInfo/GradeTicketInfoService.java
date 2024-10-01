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

    public GradeTicketInfoResponseDTO getGradeTicketInfoById(Long gradeTicketInfoId) {
        GradeTicketInfoEntity gradeTicketEntity = getGradeTicketInfoEntity(gradeTicketInfoId);

        return new GradeTicketInfoResponseDTO(gradeTicketEntity);
    }

    public List<GradeTicketInfoResponseDTO> getGradeTicketInfoByEventEntity(EventEntity eventEntity) {
        List<GradeTicketInfoResponseDTO> gradeTicketInfoResponseDTOS = new ArrayList<>();

        List<GradeTicketInfoEntity> gradeTicketEntities = gradeTicketInfoRepository.findAllByEventEntity(eventEntity);

        gradeTicketEntities.forEach(gradeTicketEntity -> {
            gradeTicketInfoResponseDTOS.add(new GradeTicketInfoResponseDTO(gradeTicketEntity));
        });

        return gradeTicketInfoResponseDTOS;
    }

    public GradeTicketInfoResponseDTO getGradeTicketInfoByEventEntityAndSeatGradeEntity(EventEntity eventEntity, SeatGradeEntity seatGradeEntity) {
        GradeTicketInfoEntity gradeTicketEntity = gradeTicketInfoRepository.findByEventEntityAndSeatGradeEntity(eventEntity, seatGradeEntity).orElseThrow(() ->
                new GlobalCommonException(GradeTicketErrorResponsive.NOT_FOUND_GRADE_TICKET));

        return new GradeTicketInfoResponseDTO(gradeTicketEntity);
    }

    public GradeTicketInfoResponseDTO createGradeTicketInfo(GradeTicketInfoRequestDTO dto,
                                                            EventEntity eventEntity,
                                                            EventLocationEntity eventLocationEntity,
                                                            SeatGradeEntity seatGradeEntity) {

        GradeTicketInfoEntity gradeTicketInfoEntity = new GradeTicketInfoEntity(dto, eventEntity, eventLocationEntity, seatGradeEntity);
        GradeTicketInfoEntity saveGradeTicketInfoEntity = gradeTicketInfoRepository.save(gradeTicketInfoEntity);

        return new GradeTicketInfoResponseDTO(saveGradeTicketInfoEntity);
    }

    public GradeTicketInfoResponseDTO updateGradeTicketInfo (Long gradeTicketInfoId,
                                                             GradeTicketInfoUpdateRequestDTO dto
                                                             ) {
        GradeTicketInfoEntity gradeTicketEntity = getGradeTicketInfoEntity(gradeTicketInfoId);

        gradeTicketEntity.update(dto);
        GradeTicketInfoEntity updateGradeTicketEntity = gradeTicketInfoRepository.save(gradeTicketEntity);

        return new GradeTicketInfoResponseDTO(updateGradeTicketEntity);
    }

    public GradeTicketInfoResponseDTO updateGradeTicketInfo (Long gradeTicketInfoId,
                                                             GradeTicketInfoUpdateRequestDTO dto,
                                                             GradeTicketInfoUpdate gradeTicketInfoUpdate) {
        GradeTicketInfoEntity gradeTicketEntity = getGradeTicketInfoEntity(gradeTicketInfoId);

        gradeTicketEntity.update(dto, gradeTicketInfoUpdate);
        GradeTicketInfoEntity updateGradeTicketEntity = gradeTicketInfoRepository.save(gradeTicketEntity);

        return new GradeTicketInfoResponseDTO(updateGradeTicketEntity);
    }

    public GradeTicketInfoResponseDTO deleteGradeTicketInfo(Long gradeTicketInfoId) {
        GradeTicketInfoEntity gradeTicketEntity = getGradeTicketInfoEntity(gradeTicketInfoId);

        gradeTicketInfoRepository.delete(gradeTicketEntity);

        return new GradeTicketInfoResponseDTO(gradeTicketEntity);
    }

    private GradeTicketInfoEntity getGradeTicketInfoEntity(Long gradeTicketInfoId) {
        GradeTicketInfoEntity gradeTicketEntity = gradeTicketInfoRepository.findById(gradeTicketInfoId).orElseThrow(() ->
                new GlobalCommonException(GradeTicketErrorResponsive.NOT_FOUND_GRADE_TICKET));
        return gradeTicketEntity;
    }

}
