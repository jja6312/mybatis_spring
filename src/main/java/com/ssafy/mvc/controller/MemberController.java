package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.dto.member.Member;
import com.ssafy.mvc.model.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberController만들어짐");
    }

    @GetMapping("createMember")
    public String createMember() {
        System.out.println("createMember호출");
        return "/member/joinForm";
    }

    @PostMapping("createMember")
    public String createMember(@ModelAttribute Member member){
        memberService.createMember(member);
        return "redirect:/";
    }
}
