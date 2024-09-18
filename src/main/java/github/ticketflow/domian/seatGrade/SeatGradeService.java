package github.ticketflow.domian.seatGrade;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.eventLocation.EventLocationErrorResponsive;
import github.ticketflow.config.exception.seatGrade.SeatGradeErrorResponsive;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.eventLocation.EventLocationRepository;
import github.ticketflow.domian.seatGrade.dto.SeatGradeRequestDTO;
import github.ticketflow.domian.seatGrade.dto.SeatGradeResponseDTO;
import github.ticketflow.domian.seatGrade.dto.SeatGradeUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatGradeService {

    private final SeatGradeRepository seatGradeRepository;
    private final EventLocationRepository eventLocationRepository;

    public SeatGradeResponseDTO getSeatGradeById(Long seatGradeId) {
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(seatGradeId);

        return new SeatGradeResponseDTO(seatGradeEntity);
    }


    @Transactional
    public  List<SeatGradeResponseDTO> getSeatGradeByEventLocationId(Long eventLocationId) {
        List<SeatGradeResponseDTO> seatGrades = new ArrayList<>();

        EventLocationEntity eventLocationEntity = getEventLocationEntity(eventLocationId);

        List<SeatGradeEntity> seatGradeEntities = seatGradeRepository.findAllByEventLocation(eventLocationEntity);
        seatGradeEntities.forEach(seatGradeEntity -> {
            seatGrades.add(new SeatGradeResponseDTO(seatGradeEntity));
        });

        return seatGrades;
    }

    @Transactional
    public SeatGradeResponseDTO createSeatGrade(SeatGradeRequestDTO dto) {
        EventLocationEntity eventLocationEntity = getEventLocationEntity(dto.getEventLocationId());
        SeatGradeEntity seatGradeEntity = new SeatGradeEntity(dto, eventLocationEntity);
        SeatGradeEntity saveSeatGradeEntity = seatGradeRepository.save(seatGradeEntity);

        return new SeatGradeResponseDTO(saveSeatGradeEntity);
    }

    @Transactional
    public SeatGradeResponseDTO updateSeatGrade(Long seatGradeId, SeatGradeUpdateRequestDTO dto) {
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(seatGradeId);
        EventLocationEntity eventLocationEntity = null;
        if (dto.getEventLocationId() != null) {
            eventLocationEntity = getEventLocationEntity(dto.getEventLocationId());
        }
        SeatGradeEntity updateSeatGradeEntity = seatGradeEntity.update(dto, eventLocationEntity);
        SeatGradeEntity saveSeatGradeEntity = seatGradeRepository.save(updateSeatGradeEntity);

        return new SeatGradeResponseDTO(saveSeatGradeEntity);

    }

    @Transactional
    public SeatGradeResponseDTO deletedSeatGrade(Long seatGradeId) {
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(seatGradeId);
        seatGradeRepository.delete(seatGradeEntity);

        return new SeatGradeResponseDTO(seatGradeEntity);
    }

    private SeatGradeEntity getSeatGradeEntity(Long seatGradeId) {
        return seatGradeRepository.findById(seatGradeId).orElseThrow(() ->
                new GlobalCommonException(SeatGradeErrorResponsive.NOT_FOUND_SEAT_GRADE)
        );
    }

    private EventLocationEntity getEventLocationEntity(Long eventLocationId) {
        return eventLocationRepository.findById(eventLocationId).orElseThrow(() ->
                new GlobalCommonException(EventLocationErrorResponsive.NOT_FOUND_EVENT_LOCATION)
        );
    }
}
