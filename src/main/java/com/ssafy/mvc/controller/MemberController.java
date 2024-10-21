package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.dto.member.LoginDto;
import com.ssafy.mvc.model.dto.member.Member;
import com.ssafy.mvc.model.exception.member.MemberCreateException;
import com.ssafy.mvc.model.exception.member.MemberLoginException;
import com.ssafy.mvc.model.service.MemberService;
import com.ssafy.mvc.model.validator.Validator;
import com.ssafy.mvc.model.validator.member.LoginDtoValidator;
import com.ssafy.mvc.model.validator.member.MemberValidator;
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
    private final Validator<Member> memberValidator;
    private final Validator<LoginDto> loginDtoValidator;

    public MemberController(MemberService memberService, MemberValidator memberValidator, LoginDtoValidator loginDtoValidator) {
        this.memberService = memberService;
        this.memberValidator = memberValidator;
        this.loginDtoValidator = loginDtoValidator;
    }

    // 회원가입 Form
    @GetMapping("createMember")
    public String createMember() {
        return "/member/joinForm";
    }

    // 회원가입
    @PostMapping("createMember")
    public String createMember(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {
        try {
            if (!memberValidator.validate(member)) throw new MemberCreateException("회원가입 유효성 검사 실패");
            memberService.createMember(member);
        } catch (MemberCreateException e) {
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
        }

        return "redirect:/";
    }

    // 로그인
    @PostMapping("login")
    public String login(@ModelAttribute LoginDto loginDto, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ServletException, IOException {
        try {
            if (!loginDtoValidator.validate(loginDto)) throw new MemberLoginException("로그인 유효성 검사 실패");

            Member member = memberService.login(loginDto);

            if (member != null) {// 로그인 성공시
                // 세션 저장
                HttpSession session = request.getSession();
                session.setAttribute("id", member.getId());
                session.setAttribute("name", member.getName());
            } else {// 로그인 실패시
                redirectAttributes.addFlashAttribute("loginError", "이메일 혹은 패스워드가 틀렸습니다.");
            }
        }catch(MemberLoginException e) {
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
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
