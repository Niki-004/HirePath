package com.jobtracker.service;

import com.jobtracker.model.Job;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobSuggestionServiceTest {

    private final JobSuggestionService suggestionService = new JobSuggestionService();

    @Test
    void suggestionForApplied() {
        Job job = new Job();
        job.setStatus("APPLIED");

        String suggestion = suggestionService.generateSuggestion(job);

        assertTrue(suggestion.toLowerCase().contains("follow up"));
    }

    @Test
    void suggestionForInReview() {
        Job job = new Job();
        job.setStatus("IN_REVIEW");

        String suggestion = suggestionService.generateSuggestion(job);

        assertTrue(suggestion.toLowerCase().contains("patient"));
    }

    @Test
    void suggestionForInterview() {
        Job job = new Job();
        job.setStatus("INTERVIEW");

        String suggestion = suggestionService.generateSuggestion(job);

        assertTrue(suggestion.toLowerCase().contains("interview"));
    }

    @Test
    void suggestionForRejected() {
        Job job = new Job();
        job.setStatus("REJECTED");

        String suggestion = suggestionService.generateSuggestion(job);

        assertTrue(suggestion.toLowerCase().contains("apply"));
    }

    @Test
    void suggestionForOffer() {
        Job job = new Job();
        job.setStatus("OFFER");

        String suggestion = suggestionService.generateSuggestion(job);

        assertTrue(suggestion.toLowerCase().contains("congrat"));
    }

    @Test
    void suggestionForUnknownStatus() {
        Job job = new Job();
        job.setStatus("SOMETHING_ELSE");

        String suggestion = suggestionService.generateSuggestion(job);

        assertTrue(suggestion.toLowerCase().contains("status unclear"));
    }

    @Test
    void suggestionForNullJob() {
        String suggestion = suggestionService.generateSuggestion(null);

        assertTrue(suggestion.toLowerCase().contains("invalid job"));
    }
}

