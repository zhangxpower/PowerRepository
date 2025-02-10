package com.fly.javanet.test1;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class AsyncHttpExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try{
            var httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .executor(executorService)
                .build();
            CompletableFuture<?>[] future = {
                    sendAsyncRequest(httpClient, "https://httpbin.org/get"),
                    sendAsyncRequest(httpClient, "https://httpbin.org/status/500"),
                    sendAsyncRequest(httpClient, "https://nonexistent.example.com")
            };
            CompletableFuture.allOf(future)
                    .exceptionally(ex -> {
                        System.out.println("Global exception: " + ex.getMessage());
                        return null;
                    })
                    .join();
        }finally {
            executorService.shutdown();
        }
    }

    private static CompletableFuture<Void> sendAsyncRequest(HttpClient httpClient, String url) {
        var request = HttpRequest.newBuilder().uri(URI.create(url))
                .timeout(Duration.ofSeconds(5))
                .header("User-Agent", "JDK11-Async-Client")
                .GET()
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApplyAsync(response -> {
                    handleResponse(response);
                    return response;
                }, ForkJoinPool.commonPool())
                .exceptionally(ex -> {
                    handleException(url, ex);
                    return null;
                })
                .thenAccept(response -> {
                    if (response != null) {
                        System.out.println("[Post-Processing] Completed: " + url);
                    }
                });
    }

    private static void handleException(String url, Throwable ex) {
        var errorMessage = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
        System.out.printf("[ERROR] %s | Failure: %s%n ", url, errorMessage);
    }

    private static void handleResponse(HttpResponse<String> response) {
        int status = response.statusCode();
        if (status >= 200 && status < 300) {
            System.out.printf("[SUCCESS] %s | Status: %d | Body length: %d%n",
                    response.uri(), status, response.body().length());
        } else {
            System.out.printf("[WARN] %s | Unexpected status: %d%n", response.uri(), status);
        }
    }

}
