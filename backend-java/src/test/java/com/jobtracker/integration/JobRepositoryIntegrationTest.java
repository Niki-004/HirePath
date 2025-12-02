package com.jobtracker.integration;

import com.jobtracker.model.Job;
import com.jobtracker.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JobRepositoryIntegrationTest {

    @Autowired
    private JobRepository jobRepository;

    @Test
    void saveAndFindAllWorks() {
        Job job = new Job();
        job.setCompany("TestCo");
        job.setPosition("Backend Intern");
        job.setStatus("APPLIED");
        job.setAppliedDate(LocalDate.now());

        jobRepository.save(job);

        List<Job> all = jobRepository.findAll();

        assertThat(all).isNotEmpty();
        assertThat(all.get(0).getCompany()).isEqualTo("TestCo");
    }
}

