package com.example.sk.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("liveness")
public class LivenessHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().withDetail("liveness", "Application is live").build();
    }
}
