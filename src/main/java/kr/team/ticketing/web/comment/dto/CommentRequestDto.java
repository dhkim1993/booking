package kr.team.ticketing.web.comment.dto;

import kr.team.ticketing.config.auth.dto.SessionUser;
import kr.team.ticketing.core.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    private Long productId;
    private int grade;
    private String review;

    public Comment toEntity(SessionUser user) {
        return Comment.builder()
                .productId(productId)
                .memberId(user.getId())
                .memberName(user.getName())
                .grade(grade)
                .review(review)
                .build();
    }
}
