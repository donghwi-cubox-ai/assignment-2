package com.test.board.controller;

import com.test.board.dto.LoginRequest;
import com.test.board.dto.RegisterRequest;
import com.test.board.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "register")
    public String register(@ModelAttribute RegisterRequest registerRequest, RedirectAttributes redirectAttributes) {
        if (!registerRequest.getPassword().equals(registerRequest.getRepeatPassword())) {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "redirect:/register";
        }
        memberService.addMember(registerRequest);
        return "redirect:/login";
    }

    @PostMapping(value = "login")
    public String login(@ModelAttribute LoginRequest loginRequest, HttpSession session, RedirectAttributes redirectAttributes) {
        boolean isAuthenticated = memberService.login(loginRequest);
        if (isAuthenticated) {
            session.setAttribute("userId", loginRequest.getUserId());
            session.setMaxInactiveInterval(60);
            return "redirect:/main";
        } else {
            redirectAttributes.addFlashAttribute("error", "다시 확인해!!!");
            return "redirect:/login";
        }
    }

    @PostMapping(value = "logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userId");
        return "redirect:/login";
    }
}
