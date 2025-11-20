package com.jobtracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String position;

    @Column(name = "job_url")
    private String jobUrl;

    @Column(nullable = false)
    private String status; // APPLIED, IN_REVIEW, REJECTED, OFFER

    @Column(name = "applied_date")
    private LocalDate appliedDate;

    @Column(length = 1000)
    private String notes; // for suggestion / extra info

    @Transient
    private Long daysSinceApplied;

    @Transient
    private String suggestion;

    public Job() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getDaysSinceApplied() {
        return daysSinceApplied;
    }

    public void setDaysSinceApplied(Long daysSinceApplied) {
        this.daysSinceApplied = daysSinceApplied;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    public Long getDaysSinceApplied() {
    if (this.appliedDate == null) {
        return null;
    }
    return java.time.temporal.ChronoUnit.DAYS.between(this.appliedDate, LocalDate.now());
}

}
