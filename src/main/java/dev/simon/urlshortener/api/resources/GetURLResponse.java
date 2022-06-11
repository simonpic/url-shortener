package dev.simon.urlshortener.api.resources;

public class GetURLResponse {
    private String fullUrl;

    public GetURLResponse(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }
}
