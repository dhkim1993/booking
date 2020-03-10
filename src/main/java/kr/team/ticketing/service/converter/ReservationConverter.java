package kr.team.ticketing.service.converter;


import kr.team.ticketing.core.domain.reservation.Reservation;
import kr.team.ticketing.web.reservation.dto.ReservationResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationConverter {

    private final ModelMapper modelMapper;

    public ReservationResponseDto convert(Reservation reservation) {
        return modelMapper.map(reservation, ReservationResponseDto.class);
    }
}
