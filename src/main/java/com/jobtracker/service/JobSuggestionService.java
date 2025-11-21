package com.jobtracker.service;

import org.springframework.stereotype.Service;

@Service
public class JobSuggestionService {

    public String suggestNextAction(String status) {
        if (status == null) return "No status available.";

        return switch (status.toUpperCase()) {
            case "APPLIED" -> "It's been a while – follow up soon!";
            case "IN_REVIEW" -> "Relax! Recruiters are reviewing your application.";
            case "INTERVIEW" -> "Prepare common interview questions!";
            case "OFFER" -> "Congrats! Review offer details carefully.";
            case "REJECTED" -> "Don't worry – keep applying. You got this!";
            default -> "Status unknown – update it for better suggestions.";
        };
    }
}

