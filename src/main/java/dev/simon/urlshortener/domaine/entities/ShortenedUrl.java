package dev.simon.urlshortener.domaine.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("urls")
public class ShortenedUrl {

    @Id
    private String shortUrl;

    private String fullUrl;

    public ShortenedUrl() {
    }

    public ShortenedUrl(String shortUrl, String fullUrl) {
        this.shortUrl = shortUrl;
        this.fullUrl = fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }
}
