package com.jobtracker.service;

import com.jobtracker.model.Job;
import com.jobtracker.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobStatsServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobStatsService jobStatsService;

    @Test
    void getStatusCounts_returnsCorrectCounts() {
        Job j1 = new Job();
        j1.setStatus("APPLIED");

        Job j2 = new Job();
        j2.setStatus("APPLIED");

        Job j3 = new Job();
        j3.setStatus("INTERVIEW");

        List<Job> jobs = Arrays.asList(j1, j2, j3);

        when(jobRepository.findAll()).thenReturn(jobs);

        Map<String, Long> stats = jobStatsService.getStatusCounts();

        assertEquals(2L, stats.get("APPLIED"));
        assertEquals(1L, stats.get("INTERVIEW"));
    }

    @Test
    void getStatusCounts_handlesEmptyList() {
        when(jobRepository.findAll()).thenReturn(List.of());

        Map<String, Long> stats = jobStatsService.getStatusCounts();

        assertEquals(0, stats.size());
    }
}

