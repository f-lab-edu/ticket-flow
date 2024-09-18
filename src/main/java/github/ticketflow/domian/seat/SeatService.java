package github.ticketflow.domian.seat;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.seat.SeatErrorResponsive;
import github.ticketflow.config.exception.seatGrade.SeatGradeErrorResponsive;
import github.ticketflow.domian.seat.dto.SeatRequestDTO;
import github.ticketflow.domian.seat.dto.SeatResponseDTO;
import github.ticketflow.domian.seat.dto.SeatUpdateRequestDTO;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import github.ticketflow.domian.seatGrade.SeatGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final SeatGradeRepository seatGradeRepository;

    public SeatResponseDTO getSeatById(Long seatId) {
        SeatEntity seatEntity = getSeatEntity(seatId);

        return new SeatResponseDTO(seatEntity);
    }

    @Transactional
    public List<SeatResponseDTO> getSeatByGradeId(Long seatGradeId) {
        List<SeatResponseDTO> seatResponseDTOList = new ArrayList<>();

        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(seatGradeId);
        List<SeatEntity> seatGradeEntities = seatRepository.findAllBySeatGradeEntity(seatGradeEntity);

        seatGradeEntities.forEach(seatEntity -> {
            seatResponseDTOList.add(new SeatResponseDTO(seatEntity));
        });

        return seatResponseDTOList;

    }

    public SeatResponseDTO createSeat(SeatRequestDTO dto) {
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(dto.getSeatGradeId());

        SeatEntity seatEntity = new SeatEntity(dto, seatGradeEntity);
        SeatEntity saveSeatEntity = seatRepository.save(seatEntity);

        return new SeatResponseDTO(saveSeatEntity);
    }

    @Transactional
    public SeatResponseDTO updateSeat(Long seatId, SeatUpdateRequestDTO dto) {
        SeatGradeEntity seatGradeEntity = null;
        if (dto.getSeatGradeId() != null) {
            seatGradeEntity = seatGradeRepository.findById(dto.getSeatGradeId()).orElseThrow(() ->
                    new GlobalCommonException(SeatErrorResponsive.NOT_FOUND_SEAT));
        }

        SeatEntity seatEntity = getSeatEntity(seatId);
        seatEntity.update(dto, seatGradeEntity);
        SeatEntity updateSeatEntity = seatRepository.save(seatEntity);

        return new SeatResponseDTO(updateSeatEntity);
    }

    @Transactional
    public SeatResponseDTO deletedSeat(Long seatId) {
        SeatEntity seatEntity = getSeatEntity(seatId);

        seatRepository.delete(seatEntity);
        return new SeatResponseDTO(seatEntity);
    }

    private SeatEntity getSeatEntity(Long seatId) {
       return seatRepository.findById(seatId).orElseThrow(() ->
                new GlobalCommonException(SeatErrorResponsive.NOT_FOUND_SEAT));
    }

    private SeatGradeEntity getSeatGradeEntity(Long seatGradeId) {
        return seatGradeRepository.findById(seatGradeId).orElseThrow(() ->
                new GlobalCommonException(SeatGradeErrorResponsive.NOT_FOUND_SEAT_GRADE));
    }
}
