package kr.team.ticketing.web.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private Long productId;
    private Long memberId;
    private String memberName;
    private int grade;
    private String review;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
