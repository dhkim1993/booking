package kr.team.ticketing.service.comment;

import kr.team.ticketing.config.auth.dto.SessionUser;
import kr.team.ticketing.core.domain.comment.Comment;
import kr.team.ticketing.core.domain.comment.CommentRepository;
import kr.team.ticketing.web.comment.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
