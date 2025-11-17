package com.jobtracker.service;

import com.jobtracker.model.Job;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class JobSuggestionService {

    public void enrich(Job job) {
        if (job == null) {
            return;
        }

        // daysSinceApplied
        if (job.getAppliedDate() != null) {
            long days = ChronoUnit.DAYS.between(job.getAppliedDate(), LocalDate.now());
            job.setDaysSinceApplied(days);
        } else {
            job.setDaysSinceApplied(null);
        }

        // suggestion based on status + days
        String status = job.getStatus();
        Long days = job.getDaysSinceApplied();
        String suggestion = "Update this application with more details.";

        if (status == null || status.isBlank()) {
            suggestion = "Set a status for this application.";
        } else {
            String s = status.toUpperCase();

            if ("APPLIED".equals(s)) {
                if (days == null) {
                    suggestion = "Add the date you applied so we can track follow-up timing.";
                } else if (days < 7) {
                    suggestion = "You recently applied. Wait a bit and monitor the portal.";
                } else if (days <= 14) {
                    suggestion = "Consider sending a polite follow-up email.";
                } else {
                    suggestion = "It’s been a while. Decide if you want to move on or keep this in your list.";
                }
            } else if ("IN_REVIEW".equals(s)) {
                suggestion = "Status is in review. Keep an eye on updates, but avoid sending too many follow-ups.";
            } else if ("REJECTED".equals(s)) {
                suggestion = "Log any feedback and see what you can improve for similar roles.";
            } else if ("OFFER".equals(s)) {
                suggestion = "Review offer details carefully and compare with your other options.";
            }
        }

        job.setSuggestion(suggestion);
    }

    public void enrich(List<Job> jobs) {
        if (jobs == null) return;
        for (Job job : jobs) {
            enrich(job);
        }
    }
}
