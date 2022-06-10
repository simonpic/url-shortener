package dev.simon.urlshortener.domaine.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("urls")
public class ShortenedUrl {

    @Id
    private String shortUrl;

    private String plainUrl;

    public ShortenedUrl() {
    }

    public ShortenedUrl(String shortUrl, String plainUrl) {
        this.shortUrl = shortUrl;
        this.plainUrl = plainUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getPlainUrl() {
        return plainUrl;
    }

    public void setPlainUrl(String plainUrl) {
        this.plainUrl = plainUrl;
    }
}
