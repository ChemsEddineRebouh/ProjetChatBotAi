package com.portfolio.ai_interviewer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private InterviewType type;

    @Enumerated(EnumType.STRING)
    private InterviewStatus status;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- Getters & Setters Manuels ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public InterviewStatus getStatus() {
        return status;
    }

    public void setStatus(InterviewStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}