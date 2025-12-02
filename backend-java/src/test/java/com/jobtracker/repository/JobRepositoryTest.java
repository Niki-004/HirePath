package com.jobtracker.repository;

import com.jobtracker.model.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @Test
    void saveAndFindAll_jobsWorkCorrectly() {
        Job job = new Job();
        job.setCompany("Amazon");
        job.setPosition("Backend Intern");
        job.setStatus("APPLIED");
        job.setAppliedDate(LocalDate.now());

        jobRepository.save(job);

        List<Job> allJobs = jobRepository.findAll();

        assertThat(allJobs).hasSize(1);
        assertThat(allJobs.get(0).getCompany()).isEqualTo("Amazon");
    }

    @Test
    void deleteById_worksCorrectly() {
        Job job = new Job();
        job.setCompany("Netflix");
        job.setPosition("SWE Intern");
        job.setStatus("APPLIED");
        job.setAppliedDate(LocalDate.now());

        Job saved = jobRepository.save(job);

        jobRepository.deleteById(saved.getId());

        List<Job> remaining = jobRepository.findAll();
        assertThat(remaining).isEmpty();
    }
}

