package com.londonsmartcity.service.environment;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class EnvironmentServer {
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentServer.class);
    private final int port;
    private final Server server;

    public EnvironmentServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
            .addService(new EnvironmentServiceImpl(port))
            .build();
    }

    public void start() throws IOException {
        server.start();
        logger.info("Environment Server started, listening on port {}", port);
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                EnvironmentServer.this.stop();
            } catch (InterruptedException e) {
                logger.error("Error shutting down server", e);
                Thread.currentThread().interrupt();
            }
        }));
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 51000;
        final EnvironmentServer server = new EnvironmentServer(port);
        server.start();
        server.blockUntilShutdown();
    }
} 