package com.jobtracker.controller;

import com.jobtracker.model.Job;
import com.jobtracker.repository.JobRepository;
import com.jobtracker.service.JobSuggestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobRepository jobRepository;

    @MockBean
    private JobSuggestionService jobSuggestionService;

    @Test
    void getSuggestion_returnsSuggestionString() throws Exception {
        Job job = new Job();
        job.setId(1L);
        job.setCompany("TestCo");
        job.setStatus("APPLIED");

        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
        when(jobSuggestionService.generateSuggestion(job))
                .thenReturn("Mock suggestion");

        mockMvc.perform(get("/api/jobs/1/suggestion"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mock suggestion"));
    }
}

