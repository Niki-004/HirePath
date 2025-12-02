package com.jobtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobtracker.model.Job;
import com.jobtracker.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobController.class)
class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobRepository jobRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllJobs_returnsEmptyList() throws Exception {
        Mockito.when(jobRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/jobs"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getJobById_existingJob_returnsJob() throws Exception {
        Job job = new Job();
        job.setId(1L);
        job.setCompany("Example Co");
        job.setPosition("Backend Developer");
        job.setStatus("APPLIED");
        job.setAppliedDate(LocalDate.now());

        Mockito.when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

        mockMvc.perform(get("/api/jobs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company").value("Example Co"))
                .andExpect(jsonPath("$.position").value("Backend Developer"));
    }

    @Test
    void createJob_validRequest_createsJob() throws Exception {
        Job toSave = new Job();
        toSave.setCompany("Example Co");
        toSave.setPosition("Backend Developer");
        toSave.setJobUrl("https://example.com/job");
        toSave.setStatus("APPLIED");

        Job saved = new Job();
        saved.setId(1L);
        saved.setCompany(toSave.getCompany());
        saved.setPosition(toSave.getPosition());
        saved.setJobUrl(toSave.getJobUrl());
        saved.setStatus(toSave.getStatus());

        Mockito.when(jobRepository.save(any(Job.class))).thenReturn(saved);

        String body = objectMapper.writeValueAsString(toSave);

        mockMvc.perform(post("/api/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.company").value("Example Co"));
    }
}

