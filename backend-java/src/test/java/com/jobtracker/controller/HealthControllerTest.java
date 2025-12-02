package com.jobtracker.controller;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class HealthControllerTest {

    private final HealthController controller = new HealthController();

    @Test
    void healthEndpointReturnsOk() {
        String result = controller.health();

        assertThat(result).isEqualTo("OK");
    }
}

