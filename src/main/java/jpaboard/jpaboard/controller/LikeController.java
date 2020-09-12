package jpaboard.jpaboard.controller;

import jpaboard.jpaboard.repository.BoardLikeRepository;
import jpaboard.jpaboard.service.LikeService;
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
    private final LikeService boardLikeService;
    private final BoardService boardService;
    private final BoardLikeRepository boardLikeRepository;

    @PostMapping("/{memberId}/{id}/{type}")
    public Map<String, String> addLike(@PathVariable("memberId") Long memberId,
                                       @PathVariable("id") Long id,
                                       @PathVariable("type") String type) {
        log.info("zzz:"+memberId+" "+id+" "+type);
        String msg = boardLikeService.addLike(memberId, id,type);
        Map<String, String> map = new HashMap<String, String>();
        map.put("msg", msg);
        return map;
    }
}
