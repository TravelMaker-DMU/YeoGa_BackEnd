package com.example.yeoga.service;

import com.example.yeoga.entity.FaqEntity;
import com.example.yeoga.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqService {

    private final FaqRepository faqRepository;

    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public List<FaqEntity> getAllFaqs() {
        return faqRepository.findAll();
    }

    public FaqEntity getFaqById(Long id) {
        return faqRepository.findById(id).orElse(null);
    }

    public FaqEntity createFaq(FaqEntity faq) {
        return faqRepository.save(faq);
    }

    public void deleteFaq(Long id) {
        faqRepository.deleteById(id);
    }
}