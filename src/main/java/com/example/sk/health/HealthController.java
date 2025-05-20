package com.example.sk.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Qualifier;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/health")
@Tag(name = "Health", description = "애플리케이션 상태 확인 API")
public class HealthController {

    private final HealthIndicator livenessIndicator;
    private final HealthIndicator readinessIndicator;

    public HealthController(
        @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
        @Qualifier("customLiveness") HealthIndicator livenessIndicator,
        @Qualifier("customReadiness") HealthIndicator readinessIndicator
    ) {
        this.livenessIndicator = livenessIndicator;
        this.readinessIndicator = readinessIndicator;
    }

    @Operation(summary = "Liveness 체크", description = "애플리케이션이 살아있는지 확인합니다.")
    @GetMapping("/liveness")
    public Health getLiveness() {
        return livenessIndicator.health();
    }

    @Operation(summary = "Readiness 체크", description = "애플리케이션이 요청을 처리할 준비가 되었는지 확인합니다.")
    @GetMapping("/readiness")
    public Health getReadiness() {
        return readinessIndicator.health();
    }
}