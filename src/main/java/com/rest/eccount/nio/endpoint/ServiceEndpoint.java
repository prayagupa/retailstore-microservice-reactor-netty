package com.rest.eccount.nio.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.eccount.nio.schema.HealthStatus;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public class ServiceEndpoint {

    private static ObjectMapper encoder = new ObjectMapper();

    public static void main(String[] args) {
        DisposableServer server =
                HttpServer.create()
                        .port(8080)
                        .route(routes ->
                                routes.get("/heartbeat",
                                        (request, response) -> {
                                            try {
                                                var healthResponse = encoder.writeValueAsString(
                                                        new HealthStatus(Instant.now().toEpochMilli(),
                                                                "app",
                                                                "1.0"
                                                        )
                                                );
                                                response.header("Content-Type", "application/json");
                                                return response.sendString(Mono.just(healthResponse));
                                            } catch (JsonProcessingException e) {

                                                response.status(500);
                                                return response.send();
                                            }
                                        })

                                        .post("/echo",
                                                (request, response) ->
                                                        response.send(request.receive().retain()))

                                        .get("/chat/{userId}",
                                                (request, response) ->
                                                        response.sendString(Mono.fromFuture(
                                                                CompletableFuture.completedFuture(request.param("userId"))
                                                        )))

                                        .ws("/ws",
                                                (wsInbound, wsOutbound) -> wsOutbound.send(wsInbound.receive().retain())))
                        .bindNow();

        server.onDispose()
                .block();
    }
}
