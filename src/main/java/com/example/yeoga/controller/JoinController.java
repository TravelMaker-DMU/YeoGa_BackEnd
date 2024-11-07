package com.example.yeoga.controller;

import com.example.yeoga.dto.JoinDTO;
import com.example.yeoga.service.JoinService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {

        this.joinService = joinService;
    }
    @Operation(description = "회원가입 하기 위한 API", summary = "회원 가입")
    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {

        joinService.joinProcess(joinDTO);

        return "ok";
    }

}
