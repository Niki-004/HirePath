package com.jobtracker.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    void daysSinceApplied_computesCorrectly() {
        Job job = new Job();
        job.setAppliedDate(LocalDate.now().minusDays(7));

        Long days = job.getDaysSinceApplied();

        assertEquals(7L, days);
    }

    @Test
    void daysSinceApplied_isNullWhenAppliedDateMissing() {
        Job job = new Job();

        Long days = job.getDaysSinceApplied();

        assertNull(days);
    }
}

