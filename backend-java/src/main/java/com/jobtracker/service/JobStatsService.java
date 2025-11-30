package com.jobtracker.service;

import com.jobtracker.model.Job;
import com.jobtracker.model.JobStats;
import com.jobtracker.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobStatsService {

    private final JobRepository jobRepository;

    public JobStatsService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobStats getStats() {
        List<Job> jobs = jobRepository.findAll();
        JobStats stats = new JobStats();

        stats.setTotalJobs((long) jobs.size());

        long applied = 0;
        long inReview = 0;
        long interview = 0;
        long rejected = 0;
        long offer = 0;
        long appliedLast7 = 0;

        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);

        for (Job job : jobs) {
            String status = job.getStatus();
            if (status != null) {
                switch (status.toUpperCase()) {
                    case "APPLIED" -> applied++;
                    case "IN_REVIEW" -> inReview++;
                    case "INTERVIEW" -> interview++;
                    case "REJECTED" -> rejected++;
                    case "OFFER" -> offer++;
                }
            }

            if (job.getAppliedDate() != null &&
                !job.getAppliedDate().isBefore(sevenDaysAgo)) {
                appliedLast7++;
            }
        }

        stats.setAppliedCount(applied);
        stats.setInReviewCount(inReview);
        stats.setInterviewCount(interview);
        stats.setRejectedCount(rejected);
        stats.setOfferCount(offer);
        stats.setAppliedLast7Days(appliedLast7);

        return stats;
    }
}

