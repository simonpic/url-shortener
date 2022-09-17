package dev.simon.urlshortener.domaine.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("urls")
public class ShortenedUrl {

    @Id
    private String hash;

    private String originalUrl;

    public ShortenedUrl() {
    }

    public ShortenedUrl(String hash, String originalUrl) {
        this.hash = hash;
        this.originalUrl = originalUrl;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
