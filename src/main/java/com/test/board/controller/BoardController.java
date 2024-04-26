package com.test.board.controller;

import com.test.board.dto.BoardRequest;
import com.test.board.service.BoardService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/")
public class BoardController {

    private final BoardService boardService;

    @PostMapping(value = "add")
    public String postBoard(@Valid @ModelAttribute BoardRequest boardRequest, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/blank";
        } else if (userId == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요한 기능입니다.");
            return "redirect:/login";
        } else {
            boardService.addBoard(boardRequest, userId);
            return "redirect:/main";
        }
    }
}
