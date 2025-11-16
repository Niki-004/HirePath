package com.jobtracker.service;

import com.jobtracker.model.Job;
import com.jobtracker.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElse(null);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> searchByCompany(String company) {
        return jobRepository.findByCompanyContainingIgnoreCase(company);
    }

    public List<Job> filterByStatus(String status) {
        return jobRepository.findByStatusIgnoreCase(status);
    }
}
