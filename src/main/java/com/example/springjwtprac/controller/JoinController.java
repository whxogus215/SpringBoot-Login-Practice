package com.example.springjwtprac.controller;

import com.example.springjwtprac.dto.JoinDTO;
import com.example.springjwtprac.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    /**
     * 1. @ModelAttribute 방식 복습 및 정리 (입력 방식이 formdata인 이유)
     */

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);

        return "ok";
    }
}
