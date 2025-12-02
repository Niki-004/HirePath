package com.jobtracker.service;

import com.jobtracker.model.Job;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JobSuggestionServiceTest {

    private final JobSuggestionService service = new JobSuggestionService();

    @Test
    void suggestionForApplied() {
        Job job = new Job();
        job.setStatus("APPLIED");

        String result = service.generateSuggestion(job);

        assertThat(result).contains("Follow up");
    }

    @Test
    void suggestionForRejected() {
        Job job = new Job();
        job.setStatus("REJECTED");

        String result = service.generateSuggestion(job);

        assertThat(result).contains("apply to 3 more");
    }

    @Test
    void suggestionForNullJob() {
        String result = service.generateSuggestion(null);

        assertThat(result).contains("Invalid");
    }

    @Test
    void suggestionForUnknownStatus() {
        Job job = new Job();
        job.setStatus("UNKNOWN");

        String result = service.generateSuggestion(job);

        assertThat(result).contains("Status unclear");
    }
}

