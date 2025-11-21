package com.jobtracker.controller;

import com.jobtracker.model.Job;
import com.jobtracker.service.JobSuggestionService;
import com.jobtracker.repository.JobRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    private final JobRepository jobRepository;
    private final JobSuggestionService suggestionService;

    public JobController(JobRepository jobRepository, JobSuggestionService suggestionService) {
        this.jobRepository = jobRepository;
        this.suggestionService = suggestionService;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job createJob(@RequestBody Job job) {
        if (job.getStatus() == null || job.getStatus().isBlank()) {
            job.setStatus("APPLIED");
        }
        return jobRepository.save(job);
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job updated) {
        Job existing = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));

        existing.setCompany(updated.getCompany());
        existing.setPosition(updated.getPosition());
        existing.setJobUrl(updated.getJobUrl());
        existing.setStatus(updated.getStatus());
        existing.setAppliedDate(updated.getAppliedDate());
        existing.setNotes(updated.getNotes());

        return jobRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable Long id) {
        if (!jobRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
        }
        jobRepository.deleteById(id);
    }

    // ------------------------------
    // NEW ENDPOINT FOR DAY 6
    // ------------------------------
    @GetMapping("/{id}/suggestion")
    public String getSuggestion(@PathVariable Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));

        return suggestionService.generateSuggestion(job);
    }
}
