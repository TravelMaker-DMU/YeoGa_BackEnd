package com.example.yeoga.controller;

import com.example.yeoga.entity.FaqEntity;
import com.example.yeoga.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faq")
public class FaqController {


    private final FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping
    public List<FaqEntity> getAllFaqs() {
        return faqService.getAllFaqs();
    }

    @GetMapping("/{id}")
    public FaqEntity getFaqById(@PathVariable Long id) {
        return faqService.getFaqById(id);
    }

    // 관리자 권한이 필요한 메서드들
    @PostMapping
    public FaqEntity createFaq(@RequestBody FaqEntity faq) {
        // 관리자 권한 체크 로직 추가 필요
        return faqService.createFaq(faq);
    }

    @DeleteMapping("/{id}")
    public void deleteFaq(@PathVariable Long id) {
        // 관리자 권한 체크 로직 추가 필요
        faqService.deleteFaq(id);
    }
}