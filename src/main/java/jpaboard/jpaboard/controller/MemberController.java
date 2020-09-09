package jpaboard.jpaboard.controller;

import jpaboard.jpaboard.RequestDto.MemberRequestDto;
import jpaboard.jpaboard.domain.Address;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(Model model){
        MemberRequestDto memberRequestDto= new MemberRequestDto();
        model.addAttribute("memberForm",memberRequestDto);
        return "members/createMember";
    }

    @PostMapping("/join")
    public String join(@Valid MemberRequestDto memberRequestDto, BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("memberForm",memberRequestDto);
            return "members/createMember";
        }
        Member member = memberRequestDto.toEntity(memberRequestDto);
        memberService.join(member);
        return "redirect:/boards/home";
    }
}
