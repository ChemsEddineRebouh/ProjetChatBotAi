package com.portfolio.ai_interviewer.service;

import com.portfolio.ai_interviewer.dto.DeepSeekRequest;
import com.portfolio.ai_interviewer.dto.DeepSeekResponse;
import com.portfolio.ai_interviewer.model.Interview;
import com.portfolio.ai_interviewer.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class InterviewService {

    private final RestClient restClient;
    private final InterviewRepository interviewRepository;

    @Value("${deepseek.api.key}")
    private String apiKey;

    public InterviewService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
        this.restClient = RestClient.builder()
                .baseUrl("https://api.deepseek.com")
                .build();
    }

    public String startInterview(Interview interview) {
        String topic = getTopicBasedOnType(interview.getType().name());

        String prompt = "You are a technical interviewer for a Java Developer role. " +
                "Generate a clear, intermediate-level multiple-choice question strictly about " + topic + ". " +
                "Keep the question text concise (max 2-3 sentences). " +
                "List options as A) B) C) D). " +
                "Do NOT include the answer yet.";

        return callDeepSeek(prompt);
    }

    public String chat(Long interviewId, String userMessage, String context) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        String topic = getTopicBasedOnType(interview.getType().name());

        if (context == null || context.isEmpty()) {
            context = "Unknown previous question.";
        }

        String prompt = String.format("""
            You are a technical interviewer.
            
            ### PREVIOUS QUESTION:
            "%s"
            
            ### CANDIDATE'S ANSWER:
            "%s"
            
            ### INSTRUCTIONS:
            1. Evaluate the answer based on the previous question.
            2. Start response with **"Correct!"** or **"Incorrect."**.
            3. Explain briefly why in 1 or 2 sentences.
            4. Ask a NEW multiple-choice question strictly about **%s**.
            5. **Keep the new question short and direct (max 3 sentences).**
            6. List options as A) B) C) D).
            """, context, userMessage, topic);

        return callDeepSeek(prompt);
    }

    private String getTopicBasedOnType(String type) {
        if ("BEHAVIORAL".equalsIgnoreCase(type)) {
            return "soft skills, conflict resolution, or teamwork";
        } else {
            return "Java Collections, Basic Design Patterns, or Standard Data Structures";
        }
    }

    private String callDeepSeek(String prompt) {
        try {
            DeepSeekRequest request = new DeepSeekRequest(prompt);

            DeepSeekResponse response = restClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .body(request)
                    .retrieve()
                    .body(DeepSeekResponse.class);

            return response != null ? response.getContent() : "Error: Empty response from DeepSeek.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error connecting to DeepSeek: " + e.getMessage();
        }
    }
}