package springboot.jpa.booking.service.converter;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import springboot.jpa.booking.core.domain.product.InfoByDate;
import springboot.jpa.booking.web.product.dto.InfoByDateResponseDto;

@Component
@RequiredArgsConstructor
public class InfoByDateConverter {

    private final ModelMapper modelMapper;

    public InfoByDateResponseDto convert(InfoByDate infoByDate) {
        return modelMapper.map(infoByDate, InfoByDateResponseDto.class);
    }
}
