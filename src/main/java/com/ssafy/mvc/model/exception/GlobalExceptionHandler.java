package com.ssafy.mvc.model.exception;

import com.ssafy.mvc.model.exception.member.MemberCreateException;
import com.ssafy.mvc.model.exception.member.MemberLoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 회원가입 예외
    @ExceptionHandler(MemberCreateException.class)
    public String handleMemberCreateException(MemberCreateException memberCreateException,  RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("msg", memberCreateException.getMessage());
        return "redirect:/member/joinForm";
    }

    // 로그인 예외
    @ExceptionHandler(MemberLoginException.class)
    public String handleMemberLoginException(MemberLoginException memberLoginException,  RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("msg", memberLoginException.getMessage());
        return "redirect:/";
    }


}
