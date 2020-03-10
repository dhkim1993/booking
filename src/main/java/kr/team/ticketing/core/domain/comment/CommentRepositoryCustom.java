package kr.team.ticketing.core.domain.comment;


public interface CommentRepositoryCustom {
    Long searchByGradeComments(int grade, Long productId);
    Long getTotalCntByProductId(Long productId);
    Double getAvg(Long productId);
}
