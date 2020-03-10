package kr.team.ticketing.web.comment.controller;

import kr.team.ticketing.config.auth.LoginUser;
import kr.team.ticketing.config.auth.dto.SessionUser;
import kr.team.ticketing.service.comment.CommentApiService;
import kr.team.ticketing.web.comment.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/comment")
public class CommentApiController {

    private final CommentApiService commentApiService;

    @PostMapping
    public String save(@RequestBody CommentRequestDto commentRequestDto, @LoginUser SessionUser user) {
        commentApiService.save(commentRequestDto, user);
        return "save success!";
    }
    @PutMapping("/{commentId}")
    public String save(@PathVariable("commentId") Long commentId,
                       @RequestBody CommentRequestDto commentRequestDto) {
        commentApiService.update(commentId, commentRequestDto);
        return "update success!";
    }
    @DeleteMapping("/{commentId}")
    public String delete(@PathVariable("commentId") Long commentId) {
        commentApiService.delete(commentId);
        return "delete success!";
    }
}
