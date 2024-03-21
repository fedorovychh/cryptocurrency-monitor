package com.example.cryptocurrencyapi.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PolygonApiClient {
    @Value("${polygon.api.url}")
    private String baseUrl;
    @Value("${polygon.api.key}")
    private String personalKey;

    public String getExchangeRatesJson(LocalDate startDate, LocalDate endDate) {
        HttpResponse<String> response;
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl.formatted(startDate, endDate, personalKey)))
                .GET()
                .build();

        try {
            response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get response from the Polygon API!", e);
        }

        return response.body();
    }
}
