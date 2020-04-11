package com.rest.eccount.nio.service;

import com.rest.eccount.nio.schema.HealthStatus;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public class EccountService {

    public CompletableFuture<HealthStatus> getHealth() {
        return CompletableFuture.completedFuture(
                new HealthStatus(Instant.now().toEpochMilli(),
                        "app",
                        "1.0"
                )
        );
    }

}
