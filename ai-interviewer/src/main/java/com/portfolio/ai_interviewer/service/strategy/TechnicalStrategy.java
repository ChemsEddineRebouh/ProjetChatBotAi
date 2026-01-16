package com.portfolio.ai_interviewer.service.strategy;

import com.portfolio.ai_interviewer.model.Interview;
import com.portfolio.ai_interviewer.model.InterviewType;
import org.springframework.stereotype.Component;

@Component
public class TechnicalStrategy implements InterviewGenerationStrategy {

    @Override
    public String generateFirstQuestion(Interview interview) {
        return "Let's dive into code. Can you explain the difference between an Interface and an Abstract Class in Java?";
    }

    @Override
    public boolean supports(String interviewType) {
        return InterviewType.TECHNICAL.name().equals(interviewType);
    }
}