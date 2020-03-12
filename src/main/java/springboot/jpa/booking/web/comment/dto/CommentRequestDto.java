package springboot.jpa.booking.web.comment.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springboot.jpa.booking.config.auth.dto.SessionUser;
import springboot.jpa.booking.core.domain.comment.Comment;

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

    @Builder
    public CommentRequestDto(Long productId, int grade, String review) {
        this.productId = productId;
        this.grade = grade;
        this.review = review;
    }
}
