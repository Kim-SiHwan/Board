package jpaboard.jpaboard.controller;

import jpaboard.jpaboard.RequestDto.MemberRequestDto;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder pwEncoder;

    @GetMapping("/join")
    public String joinForm(Model model) {
        MemberRequestDto memberRequestDto = new MemberRequestDto();
        model.addAttribute("memberForm", memberRequestDto);
        return "members/createMember";
    }

    @PostMapping("/join")
    public String join(@Valid MemberRequestDto memberRequestDto, BindingResult result, Model model) {
        model.addAttribute("memberForm", memberRequestDto);
        if (result.hasErrors()) {
            return "members/createMember";
        }
        System.out.println("pwpw:" + memberRequestDto.getPassword());

        Member member = memberRequestDto.toEntity(memberRequestDto, pwEncoder);
        memberService.join(member);
        return "redirect:/boards/home";
    }
}
