package springboot.jpa.booking.web.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.jpa.booking.core.domain.comment.CommentRepository;
import springboot.jpa.booking.service.reservation.ReservationService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepository commentRepository;
    private final ReservationService reservationService;


    @GetMapping("/{id}")
    public String writeCommentPage(@PathVariable Long id, Model model) {
        model.addAttribute("dto", reservationService.getReservationDto(id));
        return "/comment/write";
    }
}
