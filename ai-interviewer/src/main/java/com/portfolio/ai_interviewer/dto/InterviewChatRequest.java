package com.portfolio.ai_interviewer.dto;

public class InterviewChatRequest {
    private Long interviewId;
    private String userMessage;
    private String context;

    public Long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}