package kr.team.ticketing.web.comment.controller;

import kr.team.ticketing.core.domain.comment.CommentRepository;
import kr.team.ticketing.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
