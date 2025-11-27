package com.jobtracker.controller;

import com.jobtracker.model.Job;
import com.jobtracker.repository.JobRepository;
import com.jobtracker.service.JobSuggestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobSuggestionController.class)
class JobSuggestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobRepository jobRepository;

    @MockBean
    private JobSuggestionService suggestionService;

    private Job sampleJob() {
        Job job = new Job();
        job.setId(1L);
        job.setCompany("Google");
        job.setPosition("SWE Intern");
        job.setStatus("APPLIED");
        job.setAppliedDate(LocalDate.now());
        job.setNotes("High priority");
        return job;
    }

    @Test
    void getSuggestion_returnsSuggestionText() throws Exception {
        Job job = sampleJob();

        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
        when(suggestionService.generateSuggestion(job))
                .thenReturn("Follow up in 5–7 days if you haven’t heard back.");

        mockMvc.perform(get("/api/jobs/1/suggestion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.suggestion").value("Follow up in 5–7 days if you haven’t heard back."));
    }
}

