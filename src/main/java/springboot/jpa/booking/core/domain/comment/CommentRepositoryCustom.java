package springboot.jpa.booking.core.domain.comment;


public interface CommentRepositoryCustom {
    Long searchByGradeComments(int grade, Long productId);
    Long getTotalCntByProductId(Long productId);
    Double getAvg(Long productId);
}
