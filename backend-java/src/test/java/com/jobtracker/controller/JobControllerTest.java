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
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobController.class)
class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobRepository jobRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Job sampleJob() {
        Job job = new Job();
        job.setId(1L);
        job.setCompany("Google");
        job.setPosition("SWE Intern");
        job.setStatus("APPLIED");
        job.setAppliedDate(LocalDate.now());
        job.setNotes("High priority application");
        return job;
    }

    @Test
    void getJobById_returnsJob() throws Exception {
        when(jobRepository.findById(1L)).thenReturn(Optional.of(sampleJob()));

        mockMvc.perform(get("/api/jobs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company").value("Google"));
    }

    @Test
    void createJob_createsSuccessfully() throws Exception {
        Job job = sampleJob();
        job.setId(null);

        when(jobRepository.save(any(Job.class))).thenReturn(sampleJob());

        mockMvc.perform(post("/api/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(job)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.company").value("Google"));
    }
}

