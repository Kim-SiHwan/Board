package jpaboard.jpaboard.controller;

import jpaboard.jpaboard.RequestDto.BoardRequestDto;
import jpaboard.jpaboard.RequestDto.PageRequestDto;
import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.service.BoardService;
import jpaboard.jpaboard.service.MemberService;
import jpaboard.jpaboard.vo.PageMaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/home")
    public String home(Model model, PageRequestDto pageRequestDto){
        model.addAttribute("list",boardService.findAll(pageRequestDto));
        model.addAttribute("vo", boardService.makePage(pageRequestDto));
        return "/boards/home";
    }

    @GetMapping("/new")
    public String uploadForm(Model model){
        model.addAttribute("memberList",memberService.findAll());
        model.addAttribute("uploadForm", new BoardRequestDto());
        return "/boards/uploadBoard";
    }

    @PostMapping("/new")
    public String upload(Model model, @Valid BoardRequestDto boardRequestDto, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("uploadForm",boardRequestDto);
            return "/boards/uploadBoard";
        }
        Board board = boardRequestDto.toEntity(boardRequestDto);
        log.info("asd:"+boardRequestDto.getMemberId());
        boardService.upload(board, boardRequestDto.getMemberId());
        return "redirect:/boards/home";
    }

    @GetMapping("/view")
    public String viewBoard(Model model,Long id){
        boardService.addReadCount(id);
        model.addAttribute("view",boardService.findOne(id));
        return "/boards/view";
    }



}
