package springboot.jpa.booking.service.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.booking.config.auth.dto.SessionUser;
import springboot.jpa.booking.core.domain.comment.Comment;
import springboot.jpa.booking.core.domain.comment.CommentRepository;
import springboot.jpa.booking.web.comment.dto.CommentRequestDto;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentApiService {

    private final CommentRepository commentRepository;


    public Long save(CommentRequestDto commentRequestDto, SessionUser user) {
        return commentRepository.save(commentRequestDto.toEntity(user)).getId();
    }

    public Long update(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.update(commentRequestDto.getGrade(), commentRequestDto.getReview());
        return commentId;
    }

    public Long delete(Long commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }
}
