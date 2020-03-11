package springboot.jpa.booking.core.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom{
    List<Comment> findAllByProductId(Long productId);
}
