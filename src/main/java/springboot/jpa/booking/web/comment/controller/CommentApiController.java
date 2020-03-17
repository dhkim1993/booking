package springboot.jpa.booking.web.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springboot.jpa.booking.config.auth.LoginUser;
import springboot.jpa.booking.config.auth.dto.SessionUser;
import springboot.jpa.booking.service.comment.CommentApiService;
import springboot.jpa.booking.web.comment.dto.CommentRequestDto;


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
    @PutMapping(value = "/{commentId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String update(@PathVariable("commentId") Long commentId,
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
