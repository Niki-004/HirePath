package com.jobtracker.controller;

import com.jobtracker.model.Job;
import com.jobtracker.service.JobSuggestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommend")
@CrossOrigin(origins = "*")
public class JobRecommendationController {

    private final JobSuggestionService suggestionService;

    public JobRecommendationController(JobSuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @PostMapping
    public String getJobSuggestion(@RequestBody Job job) {
        return suggestionService.generateSuggestion(job);
    }
}

