package com.jobtracker.service;

import com.jobtracker.model.Job;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JobSuggestionServiceTest {

    private final JobSuggestionService service = new JobSuggestionService();

    @Test
    void suggestionIsNotBlankForBasicJob() {
        Job job = new Job();
        job.setCompany("Google");
        job.setPosition("SWE Intern");
        job.setStatus("APPLIED");
        job.setAppliedDate(LocalDate.now().minusDays(5));

        String suggestion = service.generateSuggestion(job);

        assertNotNull(suggestion);
        assertFalse(suggestion.isBlank());
    }

    @Test
    void suggestionWorksWhenAppliedLongAgo() {
        Job job = new Job();
        job.setCompany("Meta");
        job.setPosition("Backend Intern");
        job.setStatus("APPLIED");
        job.setAppliedDate(LocalDate.now().minusDays(20));

        String suggestion = service.generateSuggestion(job);

        assertNotNull(suggestion);
        assertFalse(suggestion.isBlank());
    }
}

