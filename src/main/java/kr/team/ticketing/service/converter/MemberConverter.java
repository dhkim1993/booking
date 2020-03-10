package kr.team.ticketing.service.converter;


import kr.team.ticketing.core.domain.member.Member;
import kr.team.ticketing.web.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberConverter {

    private final ModelMapper modelMapper;

    public MemberResponseDto convert(Member member) {
        return modelMapper.map(member, MemberResponseDto.class);
    }
}