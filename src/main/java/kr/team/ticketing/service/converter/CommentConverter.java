package kr.team.ticketing.service.converter;


import kr.team.ticketing.core.domain.comment.Comment;
import kr.team.ticketing.web.comment.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentConverter {

    private final ModelMapper modelMapper;

    public CommentResponseDto convert(Comment comment) {
        return modelMapper.map(comment, CommentResponseDto.class);
    }
}
