package com.jobtracker.controller;

import com.jobtracker.model.Job;
import com.jobtracker.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class JobControllerTest {

    private MockMvc mockMvc;
    private JobRepository jobRepository;

    @BeforeEach
    void setup() {
        jobRepository = Mockito.mock(JobRepository.class);
        JobController controller = new JobController(jobRepository);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetAllJobs() throws Exception {
        when(jobRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/jobs"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetJobById() throws Exception {
        Job job = new Job();
        job.setId(1L);
        job.setCompany("Google");

        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

        mockMvc.perform(get("/api/jobs/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateJob() throws Exception {
        Job saved = new Job();
        saved.setId(1L);
        saved.setCompany("Amazon");

        when(jobRepository.save(any(Job.class))).thenReturn(saved);

        mockMvc.perform(post("/api/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"company\": \"Amazon\", \"position\": \"SWE\"}"))
                .andExpect(status().isCreated());
    }
}

