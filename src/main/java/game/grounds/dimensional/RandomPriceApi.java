package game.mysteriostore;

import game.items.currency.BlueDiamond;
import game.items.currency.Diamond;
import game.items.currency.GreenDiamond;
import game.items.currency.RedDiamond;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Minimal helper that asks the API Ninjas random number endpoint for the amount of diamonds
 * that a store item should cost.
 * <p>
 * The class deliberately keeps the public surface compact so existing store wiring only needs to
 * provide the ranges for each diamond tier that an item may cost. Whenever the API cannot be
 * contacted the helper simply falls back to the minimum value that was supplied, ensuring that the
 * Mysterio Store always has a deterministic price ready.
 * </p>
 */
public final class RandomPriceApi {

    private static final Pattern DIGITS = Pattern.compile("-?\\d+");

    private final HttpClient httpClient;
    private final String apiKey;

    /**
     * @param httpClient shared HTTP client instance
     * @param apiKey     API Ninjas key used for the {@code randomnumber} endpoint
     */
    public RandomPriceApi(HttpClient httpClient, String apiKey) {
        this.httpClient = Objects.requireNonNull(httpClient);
        this.apiKey = Objects.requireNonNull(apiKey, "API key is required");
    }

    /**
     *return a price populated with the randomly selected diamond counts
     */
    public Price randomPrice(int minGreen, int maxGreen, int minBlue, int maxBlue, int minRed, int maxRed) {
        Price.Builder builder = Price.builder();
        add(builder, GreenDiamond.class, minGreen, maxGreen);
        add(builder, BlueDiamond.class, minBlue, maxBlue);
        add(builder, RedDiamond.class, minRed, maxRed);
        return builder.build();
    }

    private void add(Price.Builder builder, Class<? extends Diamond> type, int min, int max) {
        if (max <= 0) {
            return;
        }

        int lowerBound = Math.max(0, min);
        int upperBound = Math.max(lowerBound, max);
        int quantity = fetchRandomSafely(lowerBound, upperBound);

        if (quantity > 0) {
            builder.add(type, quantity);
        }
    }

    private int fetchRandomSafely(int min, int max) {
        try {
            return fetchRandom(min, max);
        } catch (RuntimeException error) {
            return min;
        }
    }

    private int fetchRandom(int min, int max) {
        URI uri = URI.create("https://api.api-ninjas.com/v1/randomnumber?min=" + min + "&max=" + max);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Accept", "application/json")
                .header("X-Api-Key", apiKey)
                .GET()
                .build();
        HttpResponse<String> response = send(request);
        if (response.statusCode() != 200) {
            throw new RuntimeException("Unexpected status code " + response.statusCode());
        }
        String body = response.body();
        if (body == null) {
            throw new RuntimeException("Empty response body");
        }
        return extractInteger(body);
    }

    private HttpResponse<String> send(HttpRequest request) {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Random number request interrupted", e);
        } catch (IOException e) {
            throw new RuntimeException("Random number request failed", e);
        }
    }

    private int extractInteger(String body) {
        Matcher matcher = DIGITS.matcher(body);
        if (!matcher.find()) {
            throw new RuntimeException("No integer found in response: " + body);
        }
        return Integer.parseInt(matcher.group());
    }
}