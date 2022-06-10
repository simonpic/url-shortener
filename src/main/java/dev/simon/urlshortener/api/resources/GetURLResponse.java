package dev.simon.urlshortener.api.resources;

public class GetURLResponse {
    private String plainUrl;

    public GetURLResponse(String plainUrl) {
        this.plainUrl = plainUrl;
    }

    public String getPlainUrl() {
        return plainUrl;
    }
}
