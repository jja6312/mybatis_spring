package com.ssafy.mvc.model.exception;

import com.ssafy.mvc.model.exception.member.MemberCreateException;
import com.ssafy.mvc.model.exception.member.MemberLoginException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 회원가입 예외
    @ExceptionHandler(MemberCreateException.class)
    public String handleMemberCreateException(MemberCreateException memberCreateException, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.setAttribute("msg", memberCreateException.getMessage());
        return "redirect:/member/joinForm";
    }

    // 로그인 예외
    @ExceptionHandler(MemberLoginException.class)
    public String handleMemberLoginException(MemberLoginException memberLoginException, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.setAttribute("msg", memberLoginException.getMessage());
        System.out.println("잡앗다");
        return "redirect:/";
    }

    // 유효성 검사 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("msg", "유효하지 않은 입력 값이 있습니다.");
        return "redirect:/";
    }
}
