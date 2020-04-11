package com.rest.eccount.nio.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.eccount.nio.service.EccountService;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.util.concurrent.CompletableFuture;

public class ServiceEndpoint {

    private static ObjectMapper encoder = new ObjectMapper();
    private static EccountService eccountService = new EccountService();

    public static void main(String[] args) {
        var server =
                HttpServer.create()
                        .port(8080)
                        .route(routes ->
                                routes.get("/heartbeat",
                                        (request, response) -> {

                                                var healthResponse = eccountService.getHealth()
                                                        .thenApply(r -> {
                                                            try {
                                                                return encoder.writeValueAsString(r);
                                                            } catch (JsonProcessingException e) {
                                                                e.printStackTrace();
                                                                return "{}";
                                                            }
                                                        });

                                                response.header("Content-Type", "application/json");
                                                return response.sendString(Mono.fromFuture(healthResponse));
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
