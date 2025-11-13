package com.jobtracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/healthz")
    public String health() {
        return "JobTracker backend running ✅";
    }
}
