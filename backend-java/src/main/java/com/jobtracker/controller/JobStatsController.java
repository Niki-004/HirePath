package com.jobtracker.controller;

import com.jobtracker.service.JobStatsService;
import com.jobtracker.repository.JobRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/jobs/stats")
@CrossOrigin(origins = "*")
public class JobStatsController {

    private final JobRepository jobRepository;
    private final JobStatsService jobStatsService;

    public JobStatsController(JobRepository jobRepository, JobStatsService jobStatsService) {
        this.jobRepository = jobRepository;
        this.jobStatsService = jobStatsService;
    }

    @GetMapping
    public Map<String, Object> getStats() {
        return jobStatsService.generateStats(jobRepository);
    }
}

