package com.jobtracker.controller;

import com.jobtracker.model.Job;
import com.jobtracker.repository.JobRepository;
import com.jobtracker.service.JobSuggestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*") // allow frontend to call during dev
public class JobController {

    private final JobRepository jobRepository;
    private final JobSuggestionService jobSuggestionService;

    public JobController(JobRepository jobRepository,
                         JobSuggestionService jobSuggestionService) {
        this.jobRepository = jobRepository;
        this.jobSuggestionService = jobSuggestionService;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        jobSuggestionService.enrich(jobs);
        return jobs;
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
        jobSuggestionService.enrich(job);
        return job;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job createJob(@RequestBody Job job) {
        if (job.getStatus() == null || job.getStatus().isBlank()) {
            job.setStatus("APPLIED");
        }
        Job saved = jobRepository.save(job);
        jobSuggestionService.enrich(saved);
        return saved;
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

        Job saved = jobRepository.save(existing);
        jobSuggestionService.enrich(saved);
        return saved;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable Long id) {
        if (!jobRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
        }
        jobRepository.deleteById(id);
    }

    @GetMapping("/status/{status}")
    public List<Job> getByStatus(@PathVariable String status) {
        List<Job> jobs = jobRepository.findByStatusIgnoreCase(status);
        jobSuggestionService.enrich(jobs);
        return jobs;
    }

    @GetMapping("/search")
    public List<Job> searchByCompany(@RequestParam("company") String company) {
        List<Job> jobs = jobRepository.findByCompanyContainingIgnoreCase(company);
        jobSuggestionService.enrich(jobs);
        return jobs;
    }

    @GetMapping("/applied-range")
    public List<Job> getByAppliedRange(@RequestParam("from") LocalDate from,
                                       @RequestParam("to") LocalDate to) {
        List<Job> jobs = jobRepository.findByAppliedDateBetween(from, to);
        jobSuggestionService.enrich(jobs);
        return jobs;
    }

    @GetMapping("/{id}/suggestion")
    public Map<String, Object> getSuggestion(@PathVariable Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
        jobSuggestionService.enrich(job);

        Map<String, Object> result = new HashMap<>();
        result.put("id", job.getId());
        result.put("status", job.getStatus());
        result.put("daysSinceApplied", job.getDaysSinceApplied());
        result.put("suggestion", job.getSuggestion());
        return result;
    }
}
