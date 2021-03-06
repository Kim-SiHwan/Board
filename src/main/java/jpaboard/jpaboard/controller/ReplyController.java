package jpaboard.jpaboard.controller;

import jpaboard.jpaboard.requestDto.ReplyRequestDto;
import jpaboard.jpaboard.responseDto.ReplyResponseDto;
import jpaboard.jpaboard.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/replies/**")
public class ReplyController {
    private final ReplyService replyService;

    private ReplyResponseDto.Result getReplies(Long boardId) {
        return replyService.findAll(boardId);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity getAllReplies(@PathVariable("boardId") Long boardId) {
        return new ResponseEntity<>(getReplies(boardId), HttpStatus.OK);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{boardId}")
    public ResponseEntity addReply(@PathVariable("boardId") Long boardId,
                                   @RequestBody ReplyRequestDto replyRequestDto) {
        replyService.addReply(replyRequestDto.toEntity(replyRequestDto), replyRequestDto.getUserName(), replyRequestDto.getBoardId());
        return new ResponseEntity<>(getReplies(boardId), HttpStatus.CREATED);
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/{boardId}/{replyId}")
    public ResponseEntity removeReply(@PathVariable("boardId") Long boardId,
                                      @PathVariable("replyId") Long replyId) {
        replyService.removeReply(replyId);
        return new ResponseEntity<>(getReplies(boardId), HttpStatus.OK);
    }


}
