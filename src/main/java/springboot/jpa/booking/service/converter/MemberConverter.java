package springboot.jpa.booking.service.converter;



import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import springboot.jpa.booking.core.domain.member.Member;
import springboot.jpa.booking.web.member.dto.MemberResponseDto;

@Component
@RequiredArgsConstructor
public class MemberConverter {

    private final ModelMapper modelMapper;

    public MemberResponseDto convert(Member member) {
        return modelMapper.map(member, MemberResponseDto.class);
    }
}