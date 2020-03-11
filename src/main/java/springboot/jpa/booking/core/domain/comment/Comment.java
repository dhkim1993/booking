package springboot.jpa.booking.core.domain.comment;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springboot.jpa.booking.core.domain.generic.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_name")
    private String memberName;

    private int grade;
    private String review;

    public void update(int grade, String review) {
        this.grade = grade;
        this.review = review;
    }
    @Builder
    public Comment(Long productId, Long memberId, String memberName, int grade, String review) {
        this.productId = productId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.grade = grade;
        this.review = review;
    }
}
