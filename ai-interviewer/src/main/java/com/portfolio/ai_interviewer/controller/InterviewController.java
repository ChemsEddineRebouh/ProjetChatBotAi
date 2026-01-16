package com.portfolio.ai_interviewer.controller;

import com.portfolio.ai_interviewer.dto.InterviewChatRequest;
import com.portfolio.ai_interviewer.dto.InterviewStartRequest;
import com.portfolio.ai_interviewer.model.Interview;
import com.portfolio.ai_interviewer.model.InterviewStatus;
import com.portfolio.ai_interviewer.repository.InterviewRepository;
import com.portfolio.ai_interviewer.service.InterviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/interviews")
@CrossOrigin(origins = "http://localhost:5173")
public class InterviewController {

    private final InterviewService interviewService;
    private final InterviewRepository interviewRepository;

    public InterviewController(InterviewService interviewService, InterviewRepository interviewRepository) {
        this.interviewService = interviewService;
        this.interviewRepository = interviewRepository;
    }

    @PostMapping("/start")
    public ResponseEntity<?> startInterview(@RequestBody InterviewStartRequest request) {
        Interview interview = new Interview();
        interview.setTitle(request.getTitle());
        interview.setType(request.getType());
        interview.setStatus(InterviewStatus.PENDING);

        interviewRepository.save(interview);

        String firstQuestion = interviewService.startInterview(interview);

        return ResponseEntity.ok(Map.of(
                "interviewId", interview.getId(),
                "firstQuestion", firstQuestion
        ));
    }

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody InterviewChatRequest request) {
        String aiResponse = interviewService.chat(
                request.getInterviewId(),
                request.getUserMessage(),
                request.getContext()
        );

        return ResponseEntity.ok(Map.of(
                "response", aiResponse
        ));
    }
}