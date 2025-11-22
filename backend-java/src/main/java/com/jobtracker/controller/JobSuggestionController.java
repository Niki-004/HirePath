package com.jobtracker.controller;

import com.jobtracker.model.Job;
import com.jobtracker.service.JobSuggestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suggestions")
@CrossOrigin(origins = "*")
public class JobSuggestionController {

    private final JobSuggestionService suggestionService;

    public JobSuggestionController(JobSuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @PostMapping
    public String getSuggestion(@RequestBody Job job) {
        return suggestionService.generateSuggestion(job);
    }
}

