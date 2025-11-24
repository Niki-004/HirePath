package com.jobtracker.service;

import com.jobtracker.model.Job;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JobSuggestionServiceTest {

    private final JobSuggestionService service = new JobSuggestionService();

    @Test
    void suggestionForApplied() {
        Job job = new Job();
        job.setStatus("APPLIED");

        String result = service.generateSuggestion(job);

        assertTrue(result.contains("Follow up"));
    }

    @Test
    void suggestionForReview() {
        Job job = new Job();
        job.setStatus("IN_REVIEW");

        String result = service.generateSuggestion(job);

        assertTrue(result.contains("Be patient"));
    }

    @Test
    void suggestionForInterview() {
        Job job = new Job();
        job.setStatus("INTERVIEW");

        String result = service.generateSuggestion(job);

        assertTrue(result.contains("Prepare"));
    }

    @Test
    void suggestionForRejected() {
        Job job = new Job();
        job.setStatus("REJECTED");

        String result = service.generateSuggestion(job);

        assertTrue(result.contains("apply to 3 more"));
    }

    @Test
    void suggestionForOffer() {
        Job job = new Job();
        job.setStatus("OFFER");

        String result = service.generateSuggestion(job);

        assertTrue(result.contains("Congratulations"));
    }

    @Test
    void suggestionForNullStatus() {
        Job job = new Job();
        job.setStatus(null);

        String result = service.generateSuggestion(job);

        assertTrue(result.contains("Status unclear"));
    }
}

