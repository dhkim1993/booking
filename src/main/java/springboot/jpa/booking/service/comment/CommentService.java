package springboot.jpa.booking.service.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.booking.core.domain.comment.Comment;
import springboot.jpa.booking.core.domain.comment.CommentRepository;
import springboot.jpa.booking.service.converter.CommentConverter;
import springboot.jpa.booking.web.comment.dto.CommentResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;


    public double getAvg(Long productId) {
        return commentRepository.getAvg(productId);
    }

    public Long getTotalCnt(Long productId) {
        return commentRepository.getTotalCntByProductId(productId);
    }

    public List<Long> searchByGradeComments(Long productId) {
        List<Long> gradeCntList = new ArrayList<>();
        gradeCntList.add(commentRepository.searchByGradeComments(5, productId));
        gradeCntList.add(commentRepository.searchByGradeComments(4, productId));
        gradeCntList.add(commentRepository.searchByGradeComments(3, productId));
        gradeCntList.add(commentRepository.searchByGradeComments(2, productId));
        gradeCntList.add(commentRepository.searchByGradeComments(1, productId));
        return gradeCntList;
    }

    public List<CommentResponseDto>findAllDtoByProductId(Long productId) {
        List<Comment> all = commentRepository.findAllByProductId(productId);
        return all.stream().map(commentConverter::convert).collect(Collectors.toList());
    }
}
