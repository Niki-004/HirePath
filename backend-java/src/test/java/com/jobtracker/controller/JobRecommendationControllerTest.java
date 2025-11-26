package com.jobtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobtracker.model.Job;
import com.jobtracker.service.JobSuggestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class JobRecommendationControllerTest {

    private MockMvc mockMvc;
    private JobSuggestionService suggestionService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        suggestionService = Mockito.mock(JobSuggestionService.class);
        JobRecommendationController controller =
                new JobRecommendationController(suggestionService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void returnsSuggestionFromService() throws Exception {
        when(suggestionService.generateSuggestion(any(Job.class)))
                .thenReturn("mock-suggestion");

        Job job = new Job();
        job.setCompany("Meta");
        job.setStatus("APPLIED");

        mockMvc.perform(post("/api/recommend")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(job)))
                .andExpect(status().isOk())
                .andExpect(content().string("mock-suggestion"));
    }
}

