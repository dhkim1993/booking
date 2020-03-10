package kr.team.ticketing.service.converter;

import kr.team.ticketing.core.domain.product.InfoByDate;
import kr.team.ticketing.web.product.dto.InfoByDateResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InfoByDateConverter {

    private final ModelMapper modelMapper;

    public InfoByDateResponseDto convert(InfoByDate infoByDate) {
        return modelMapper.map(infoByDate, InfoByDateResponseDto.class);
    }
}
