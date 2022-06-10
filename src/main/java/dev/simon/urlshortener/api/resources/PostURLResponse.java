package dev.simon.urlshortener.api.resources;

public class PostURLResponse {
    private String shortenedUrl;

    public PostURLResponse(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }
}
