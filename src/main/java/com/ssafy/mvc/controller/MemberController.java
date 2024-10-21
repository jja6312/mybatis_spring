package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.dto.member.LoginDto;
import com.ssafy.mvc.model.dto.member.Member;
import com.ssafy.mvc.model.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("createMember")
    public String createMember() {
        return "/member/joinForm";
    }

    // 회원가입
    @PostMapping("createMember")
    public String createMember(@ModelAttribute Member member) {
        memberService.createMember(member);
        return "redirect:/";
    }

    // 로그인
    @PostMapping("login")
    public String login(@ModelAttribute LoginDto loginDto, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ServletException, IOException {
        System.out.println(loginDto);
        Member member = memberService.login(loginDto);
        System.out.println(member);
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("id", member.getId());
            session.setAttribute("name", member.getName());
        } else {
            redirectAttributes.addFlashAttribute("loginError", "이메일 혹은 패스워드가 틀렸습니다.");
        }
        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession(false).invalidate();
        return "redirect:/";
    }
}
