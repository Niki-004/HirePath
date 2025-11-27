package com.jobtracker.repository;

import com.jobtracker.model.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    private Job sampleJob() {
        Job job = new Job();
        job.setCompany("Apple");
        job.setPosition("Software Engineer Intern");
        job.setStatus("APPLIED");
        job.setAppliedDate(LocalDate.now());
        job.setNotes("Excited for this role!");
        return job;
    }

    @Test
    void saveJob_savesCorrectly() {
        Job job = sampleJob();
        Job saved = jobRepository.save(job);

        assertNotNull(saved.getId());
        assertEquals("Apple", saved.getCompany());
    }

    @Test
    void findAll_returnsJobs() {
        Job job = sampleJob();
        jobRepository.save(job);

        List<Job> list = jobRepository.findAll();

        assertFalse(list.isEmpty());
        assertEquals("Apple", list.get(0).getCompany());
    }

    @Test
    void deleteJob_removesEntry() {
        Job job = sampleJob();
        Job saved = jobRepository.save(job);

        jobRepository.deleteById(saved.getId());

        assertFalse(jobRepository.findById(saved.getId()).isPresent());
    }
}

