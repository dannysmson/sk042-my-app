package com.example.sk.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("readiness")
public class ReadinessHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        boolean ready = true; // 예: DB ping, API 연결 확인 등 로직 추가 가능
        return ready ? Health.up().withDetail("readiness", "Application is ready").build()
                     : Health.down().withDetail("readiness", "Not ready").build();
    }
}
