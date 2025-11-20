package com.jobtracker.repository;

import com.jobtracker.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByStatusIgnoreCase(String status);

    List<Job> findByCompanyContainingIgnoreCase(String company);

    List<Job> findByAppliedDateBetween(LocalDate from, LocalDate to);
}
