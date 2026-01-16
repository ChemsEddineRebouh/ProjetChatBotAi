package com.portfolio.ai_interviewer.service.strategy;

import com.portfolio.ai_interviewer.model.Interview;
import com.portfolio.ai_interviewer.model.InterviewType;
import org.springframework.stereotype.Component;

@Component
public class BehavioralStrategy implements InterviewGenerationStrategy {

    @Override
    public String generateFirstQuestion(Interview interview) {
        return "Tell me about a time you faced a difficult conflict with a colleague. How did you handle it?";
    }

    @Override
    public boolean supports(String interviewType) {
        return InterviewType.BEHAVIORAL.name().equals(interviewType);
    }
}