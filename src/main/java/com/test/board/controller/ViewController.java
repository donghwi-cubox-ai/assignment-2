package com.test.board.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ViewController {

    @GetMapping(value = "main")
    public String main() {
        return "index";
    }

    @GetMapping(value = "login")
    public String login(HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "login";
        } else {
            redirectAttributes.addFlashAttribute("error", "로그아웃 필요!!!");
            return "redirect:/main";
        }
    }

    @GetMapping(value = "register")
    public String register(HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "register";
        } else {
            redirectAttributes.addFlashAttribute("error", "로그아웃 필요!!!");
            return "redirect:/main";
        }
    }

    @GetMapping(value = "blank")
    public String blank(HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            redirectAttributes.addFlashAttribute("error", "로그인 해!!!");
            return "redirect:/login";
        } else {
            return "blank";
        }
    }
}
