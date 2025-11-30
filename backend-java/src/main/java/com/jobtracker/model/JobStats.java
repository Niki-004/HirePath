package com.jobtracker.model;

public class JobStats {

    private long totalJobs;
    private long appliedCount;
    private long inReviewCount;
    private long interviewCount;
    private long rejectedCount;
    private long offerCount;

    public JobStats() {
    }

    public JobStats(long totalJobs,
                    long appliedCount,
                    long inReviewCount,
                    long interviewCount,
                    long rejectedCount,
                    long offerCount) {
        this.totalJobs = totalJobs;
        this.appliedCount = appliedCount;
        this.inReviewCount = inReviewCount;
        this.interviewCount = interviewCount;
        this.rejectedCount = rejectedCount;
        this.offerCount = offerCount;
    }

    public long getTotalJobs() {
        return totalJobs;
    }

    public void setTotalJobs(long totalJobs) {
        this.totalJobs = totalJobs;
    }

    public long getAppliedCount() {
        return appliedCount;
    }

    public void setAppliedCount(long appliedCount) {
        this.appliedCount = appliedCount;
    }

    public long getInReviewCount() {
        return inReviewCount;
    }

    public void setInReviewCount(long inReviewCount) {
        this.inReviewCount = inReviewCount;
    }

    public long getInterviewCount() {
        return interviewCount;
    }

    public void setInterviewCount(long interviewCount) {
        this.interviewCount = interviewCount;
    }

    public long getRejectedCount() {
        return rejectedCount;
    }

    public void setRejectedCount(long rejectedCount) {
        this.rejectedCount = rejectedCount;
    }

    public long getOfferCount() {
        return offerCount;
    }

    public void setOfferCount(long offerCount) {
        this.offerCount = offerCount;
    }
}

