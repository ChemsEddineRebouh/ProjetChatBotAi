package com.portfolio.ai_interviewer.dto;

import com.portfolio.ai_interviewer.model.InterviewType;

public class InterviewStartRequest {
    private String title;
    private InterviewType type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InterviewType getType() {
        return type;
    }

    public void setType(InterviewType type) {
        this.type = type;
    }
}