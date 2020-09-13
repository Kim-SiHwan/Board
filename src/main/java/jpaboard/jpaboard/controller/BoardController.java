package jpaboard.jpaboard.controller;

import jpaboard.jpaboard.RequestDto.BoardRequestDto;
import jpaboard.jpaboard.RequestDto.PageRequestDto;
import jpaboard.jpaboard.domain.Board;
import jpaboard.jpaboard.service.BoardService;
import jpaboard.jpaboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/home")
    public String home(Model model, PageRequestDto pageRequestDto){
        model.addAttribute("list",boardService.findAll(pageRequestDto));
        model.addAttribute("vo", boardService.makePage(pageRequestDto));
        return "/boards/home";
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/new")
    public String uploadForm(Model model){
        model.addAttribute("uploadForm", new BoardRequestDto());
        return "/boards/uploadBoard";
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @PostMapping("/new")
    public String upload(Model model, @Valid BoardRequestDto boardRequestDto, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("uploadForm",boardRequestDto);
            return "/boards/uploadBoard";
        }
        Board board = boardRequestDto.toEntity(boardRequestDto);
        boardService.upload(board, boardRequestDto.getUserName());
        return "redirect:/boards/home";
    }

    @GetMapping("/view")
    public String viewBoard(Model model,Long boardId){
        boardService.addReadCount(boardId);
        model.addAttribute("view",boardService.findOne(boardId));
        return "/boards/view";
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/update")
    public String updateForm(Model model,Long boardId){
        model.addAttribute("view",boardService.findOne(boardId));
        return "/boards/updateBoard";
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @PutMapping("/update")
    public String updateBoard(BoardRequestDto boardRequestDto , RedirectAttributes rt){
        Board board = boardRequestDto.toEntity(boardRequestDto);
        boardService.upload(board,boardRequestDto.getUserName());
        rt.addAttribute("boardId",boardRequestDto.getBoardId());
        return "redirect:/boards/view";
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @DeleteMapping("/remove")
    public String removeBoard(Long boardId){
        boardService.removeBoard(boardId);
        return "redirect:/boards/home";
    }

    @GetMapping("/best_home")
    public String bestHome(Model model, PageRequestDto pageRequestDto){
        model.addAttribute("list",boardService.findAllByLike(pageRequestDto));
        model.addAttribute("vo", boardService.makePageByBest(pageRequestDto));
        return "/boards/home";
    }




}
