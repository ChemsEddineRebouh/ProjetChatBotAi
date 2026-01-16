package com.portfolio.ai_interviewer.repository;

import com.portfolio.ai_interviewer.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
}