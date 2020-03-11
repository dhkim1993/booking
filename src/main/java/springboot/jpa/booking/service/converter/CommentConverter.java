package springboot.jpa.booking.service.converter;



import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import springboot.jpa.booking.core.domain.comment.Comment;
import springboot.jpa.booking.web.comment.dto.CommentResponseDto;

@Component
@RequiredArgsConstructor
public class CommentConverter {

    private final ModelMapper modelMapper;

    public CommentResponseDto convert(Comment comment) {
        return modelMapper.map(comment, CommentResponseDto.class);
    }
}
