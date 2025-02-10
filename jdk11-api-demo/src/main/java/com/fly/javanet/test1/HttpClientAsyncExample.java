package com.fly.javanet.test1;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

public class HttpClientAsyncExample {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.baidu.com"))
                .POST(HttpRequest.BodyPublishers.ofString("hello"))
                .build();
        CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString(Charset.defaultCharset()));
        future.whenComplete((r, t) -> {
            System.out.println(r.statusCode());
            System.out.println(r.body());
            System.out.println(r.headers());
            System.out.println(t.getMessage());
        });
        CompletableFuture.allOf(future).join();
    }
}