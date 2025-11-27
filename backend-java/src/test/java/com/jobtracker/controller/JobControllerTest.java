package com.jobtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobtracker.model.Job;
import com.jobtracker.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobController.class)
class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JobRepository jobRepository;

    private Job sampleJob() {
        Job job = new Job();
        job.setId(1L);
        job.setCompany("Test Company");
        job.setPosition("Backend Intern");
        job.setJobUrl("https://example.com/job");
        job.setStatus("APPLIED");
        job.setAppliedDate(LocalDate.of(2025, 1, 1));
        job.setNotes("Test notes");
        return job;
    }

    @Test
    void getAllJobs_returnsList() throws Exception {
        Job job = sampleJob();
        given(jobRepository.findAll()).willReturn(Collections.singletonList(job));

        mockMvc.perform(get("/api/jobs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].company").value("Test Company"))
                .andExpect(jsonPath("$[0].position").value("Backend Intern"));
    }

    @Test
    void getJobById_returnsJob() throws Exception {
        Job job = sampleJob();
        given(jobRepository.findById(1L)).willReturn(Optional.of(job));

        mockMvc.perform(get("/api/jobs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company").value("Test Company"))
                .andExpect(jsonPath("$.position").value("Backend Intern"));
    }

    @Test
    void createJob_createsAndReturnsJob() throws Exception {
        Job job = sampleJob();
        job.setId(null); // when sending request, ID is not set

        Job saved = sampleJob();
        given(jobRepository.save(job)).willReturn(saved);

        String body = objectMapper.writeValueAsString(job);

        mockMvc.perform(post("/api/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.company").value("Test Company"))
                .andExpect(jsonPath("$.status").value("APPLIED"));

        verify(jobRepository).save(job);
    }
}

