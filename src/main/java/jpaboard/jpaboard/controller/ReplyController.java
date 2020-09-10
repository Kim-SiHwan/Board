package jpaboard.jpaboard.controller;

import jpaboard.jpaboard.RequestDto.ReplyRequestDto;
import jpaboard.jpaboard.domain.Reply;
import jpaboard.jpaboard.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/replies/**")
public class ReplyController {
    private final ReplyService replyService;

    private List<Reply> getReplies(Long boardId){
        return replyService.findAll(boardId);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity getAllReplies(@PathVariable("boardId") Long boardId){
        return new ResponseEntity<>(getReplies(boardId),HttpStatus.OK);
    }

    @PostMapping("/{boardId}")
    public ResponseEntity addReply(@PathVariable("boardId") Long boardId,
                                   @RequestBody ReplyRequestDto replyRequestDto){
        log.info("zzz:"+boardId);
        log.info("zzz:"+replyRequestDto.getContent());
        log.info("zzz:"+replyRequestDto.getMemberId());
        log.info("zzz:"+replyRequestDto.getBoardId());

        replyService.addReply(replyRequestDto.toEntity(replyRequestDto), replyRequestDto.getMemberId(), replyRequestDto.getBoardId());
        return new ResponseEntity<>(getReplies(boardId),HttpStatus.CREATED);
    }



}
