package jpaboard.jpaboard.controller;

import jpaboard.jpaboard.repository.BoardLikeRepository;
import jpaboard.jpaboard.service.LikeService;
import jpaboard.jpaboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/{id}/{type}")
    public Map<String, String> addLike(@PathVariable("id") Long id,
                                       @PathVariable("type") String type,
                                       Principal principal) {
        String msg = boardLikeService.addLike(principal.getName(), id, type);
        Map<String, String> map = new HashMap<String, String>();
        map.put("msg", msg);
        return map;
    }
}
