package com.jobtracker.service;

import com.jobtracker.model.Job;
import org.springframework.stereotype.Service;

@Service
public class JobSuggestionService {

    public String generateSuggestion(Job job) {

        if (job == null) {
            return "Invalid job information provided.";
        }

        String status = job.getStatus() != null ? job.getStatus().toUpperCase() : "";

        switch (status) {
            case "APPLIED":
                return "Follow up in 5-7 days if you haven’t heard back.";
            case "IN_REVIEW":
                return "Be patient! Companies may take 1–3 weeks to review.";
            case "INTERVIEW":
                return "Prepare by reviewing the job description and common interview questions.";
            case "REJECTED":
                return "Don’t feel bad — apply to 3 more similar roles and improve your resume.";
            case "OFFER":
                return "Congratulations! Compare salary + benefits before accepting.";
            default:
                return "Status unclear — add more details for a better suggestion.";
        }
    }
}

