package jpaboard.jpaboard.controller;

import jpaboard.jpaboard.repository.BoardLikeRepository;
import jpaboard.jpaboard.service.BoardLikeService;
import jpaboard.jpaboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/like/**")
public class LikeController {
    private final BoardLikeService boardLikeService;
    private final BoardService boardService;
    private final BoardLikeRepository boardLikeRepository;
    @PostMapping("/{memberId}/{boardId}")
    public Map<String,String> addLike(@PathVariable("memberId") Long memberId,
                       @PathVariable("boardId") Long boardId){
        String msg = boardLikeService.addLike(memberId,boardId);
        Map<String, String> map = new HashMap<String, String>();
        map.put("msg",msg);
        return map;
    }
}
