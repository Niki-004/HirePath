package com.jobtracker.controller;

import com.jobtracker.model.Job;
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

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // GET all jobs
    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // GET job by ID
    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Job not found"
                ));
    }

    // POST create job
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job createJob(@RequestBody Job job) {

        // default status if missing
        if (job.getStatus() == null || job.getStatus().isBlank()) {
            job.setStatus("APPLIED");
        }

        return jobRepository.save(job);
    }

    // PUT update job
    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job updated) {

        Job existing = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Job not found"
                ));

        existing.setCompany(updated.getCompany());
        existing.setPosition(updated.getPosition());
        existing.setJobUrl(updated.getJobUrl());
        existing.setStatus(updated.getStatus());
        existing.setAppliedDate(updated.getAppliedDate());
        existing.setNotes(updated.getNotes());

        return jobRepository.save(existing);
    }

    // DELETE job
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable Long id) {

        if (!jobRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Job not found"
            );
        }

        jobRepository.deleteById(id);
    }
}
