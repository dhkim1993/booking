package springboot.jpa.booking.service.converter;



import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import springboot.jpa.booking.core.domain.reservation.Reservation;
import springboot.jpa.booking.web.reservation.dto.ReservationResponseDto;

@Component
@RequiredArgsConstructor
public class ReservationConverter {

    private final ModelMapper modelMapper;

    public ReservationResponseDto convert(Reservation reservation) {
        return modelMapper.map(reservation, ReservationResponseDto.class);
    }
}
