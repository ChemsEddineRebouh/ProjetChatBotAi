package com.portfolio.ai_interviewer.service.strategy;

import com.portfolio.ai_interviewer.model.Interview;

public interface InterviewGenerationStrategy {
    String generateFirstQuestion(Interview interview);
    boolean supports(String interviewType);
}