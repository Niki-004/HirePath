package com.jobtracker.controller;

import com.jobtracker.model.Job;
import com.jobtracker.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class JobStatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobRepository jobRepository;

    @Test
    void testStatsEndpoint() throws Exception {
        jobRepository.deleteAll();

        Job j1 = new Job();
        j1.setCompany("Google");
        j1.setPosition("SWE");
        j1.setStatus("APPLIED");
        j1.setAppliedDate(LocalDate.now());
        jobRepository.save(j1);

        Job j2 = new Job();
        j2.setCompany("Meta");
        j2.setPosition("Backend Intern");
        j2.setStatus("REJECTED");
        j2.setAppliedDate(LocalDate.now().minusDays(10));
        jobRepository.save(j2);

        mockMvc.perform(get("/api/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(2))
                .andExpect(jsonPath("$.rejected").value(1));
    }
}

